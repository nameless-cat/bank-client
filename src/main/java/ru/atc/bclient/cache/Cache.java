package ru.atc.bclient.cache;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.atc.bclient.model.entities.dim.AccountStatus;
import ru.atc.bclient.model.entities.dim.Bank;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;
import ru.atc.bclient.model.service.AccountBalanceService;
import ru.atc.bclient.model.service.AccountStatusService;
import ru.atc.bclient.model.service.BankService;
import ru.atc.bclient.model.service.PaymentOrderStatusService;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {

    private static Cache cache;

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountStatusService accountStatusService;

    @Autowired
    private PaymentOrderStatusService paymentOrderStatusService;

    private ConcurrentHashMap<Integer, AccountStatus> accountsStatuses = new ConcurrentHashMap();

    private ConcurrentHashMap<Integer, PaymentOrderStatus> paymentOrderStatuses = new ConcurrentHashMap();

    private ConcurrentHashMap<Integer, Bank> banks = new ConcurrentHashMap();

    public static Cache getCache() {
        return cache;
    }

    @PostConstruct
    private void setCache(){
        cache = this;
    }

    public AccountStatus getAccountStatusById(int id) {
        return accountsStatuses.computeIfAbsent(id, k -> accountStatusService.getById(k));
    }

    public PaymentOrderStatus getPaymentStatusById(int id) {
        return paymentOrderStatuses.computeIfAbsent(id, k->paymentOrderStatusService.getById(id));
    }

    public Bank getBankById(int id) {
        return banks.computeIfAbsent(id, k->bankService.getById(id));
    }


}
