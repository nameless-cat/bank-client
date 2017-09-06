package ru.atc.bclient.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.atc.bclient.cache.Cache;
import ru.atc.bclient.model.entities.dim.Account;
import ru.atc.bclient.model.entities.fct.AccountBalance;
import ru.atc.bclient.model.entities.fct.Operation;
import ru.atc.bclient.model.entities.fct.PaymentOrder;
import ru.atc.bclient.model.service.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentOrderHandler {
    //    public static void main(String[] args) {
//        new PaymentOrderHandler().perform(new ArrayList<>());
//    }
    @Autowired
    private PaymentOrderService paymentOrderService;
    @Autowired
    private AccountBalanceService accountBalanceService;
    @Autowired
    private AccountService accountService;
    @Autowired
    Cache cache;
    @Autowired
    private OperationService operationService;

    List<PaymentOrder> notEnoughMoney = new ArrayList<>();

    private volatile boolean handling;


    public synchronized void handle() {
        handling = true;

//        System.out.println("handling");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<PaymentOrder> allNotHandled = paymentOrderService.getAllNotHandled();
        for (PaymentOrder paymentOrder : allNotHandled) {
            if (checkMoney(paymentOrder)) {
                if (checkAccountsAvailability(paymentOrder)){
                    rejectAccountNotAvailable(paymentOrder);
                }
                perform(paymentOrder);
            } else {
                notEnoughMoney.add(paymentOrder);
            }
        }
        for (PaymentOrder paymentOrder : notEnoughMoney) {
            if (checkMoney(paymentOrder)) {
                perform(paymentOrder);
            } else {
                rejectNoMoney(paymentOrder);
            }
        }
        handling = false;
    }



    private boolean checkAccountsAvailability(PaymentOrder paymentOrder) {
        return paymentOrder.getSenderAccount().getStatus().equals(cache.getAccountStatusById(1))
                && paymentOrder.getRecipientAccount().getStatus().equals(cache.getAccountStatusById(1));
    }

    @Transactional
    protected void perform(PaymentOrder paymentOrder) {
        Date today = Date.valueOf(LocalDate.now());
        AccountBalance senderAccountBalance = getNewestAccountBalance(paymentOrder.getSenderAccount());
        BigDecimal newSenderBalanceAmount = senderAccountBalance.getAmount().subtract(paymentOrder.getOrderAmount());
        senderAccountBalance.setAmount(newSenderBalanceAmount);

        AccountBalance recipientAccountBalance = getNewestAccountBalance(paymentOrder.getRecipientAccount());
        BigDecimal newRecipientBalance = recipientAccountBalance.getAmount().add(paymentOrder.getOrderAmount());
        recipientAccountBalance.setAmount(newRecipientBalance);

        Operation operation = new Operation();
        operation.setAmount(paymentOrder.getOrderAmount());
        operation.setDate(today);
        operation.setDescription(paymentOrder.getReason());
        operation.setCreditAccount(paymentOrder.getSenderAccount());
        operation.setDebetAccount(paymentOrder.getRecipientAccount());

        paymentOrder.setStatus(cache.getPaymentStatusById(5));

        accountBalanceService.save(recipientAccountBalance);
        accountBalanceService.save(senderAccountBalance);
        paymentOrderService.save(paymentOrder);
        operationService.save(operation);


    }

    private AccountBalance getNewestAccountBalance(Account account) {
        AccountBalance newestByAccountId = accountBalanceService.getNewestByAccountId(account.getId());
        if (!newestByAccountId.getDate().equals(Date.valueOf(LocalDate.now()))) {
            BigDecimal currentBalance = newestByAccountId.getAmount();
            newestByAccountId = new AccountBalance();
            newestByAccountId.setDate(Date.valueOf(LocalDate.now()));
            newestByAccountId.setAccount(account);
            newestByAccountId.setAmount(currentBalance);
        }
        return newestByAccountId;
    }

    private void rejectNoMoney(PaymentOrder paymentOrder) {
        paymentOrder.setStatus(cache.getPaymentStatusById(6));
        paymentOrder.setRejectReason("Недостаточно денег на счете");
        paymentOrderService.save(paymentOrder);
    }

    private void rejectAccountNotAvailable(PaymentOrder paymentOrder) {
        paymentOrder.setStatus(cache.getPaymentStatusById(6));
        paymentOrder.setRejectReason("Один из счетов не активыный");
    }


    private boolean checkMoney(PaymentOrder paymentOrder) {
        BigDecimal balance = accountBalanceService.getNewestByAccountId(paymentOrder.getSenderAccount().getId()).getAmount();
        BigDecimal amount = paymentOrder.getOrderAmount();
        return balance.compareTo(amount) >= 0;
    }


    public boolean isHandling() {
        return handling;
    }
}
