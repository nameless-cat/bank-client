package ru.atc.bclient.web.controller.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.atc.bclient.cache.Cache;
import ru.atc.bclient.model.entities.dim.Account;
import ru.atc.bclient.model.entities.dim.Contract;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;
import ru.atc.bclient.model.entities.fct.PaymentOrder;
import ru.atc.bclient.model.service.PaymentOrderService;
import ru.atc.bclient.model.service.PaymentOrderStatusService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Component
@SessionScope
public class PaymentOrderCreator {



    @Autowired
    private PaymentOrderService paymentOrderService;

    @Autowired
    Cache cache;

    private int num;

    private Date date;

    private LegalEntity senderLegalEntity;

    private Account senderAccount;

    private LegalEntity recipientLegalEntity;

    private Account recipientAccount;

    private Contract contract;

    private String currencyCode;

    private String orderAmount;

    private String reason;

    private String priorityCode;

    private PaymentOrderStatus paymentOrderStatus;

    private String rejectReason;

    public Account getSenderAccount() {
        return senderAccount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public Contract getContract() {
        return contract;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSenderLegalEntity(LegalEntity senderLegalEntity) {
        this.senderLegalEntity = senderLegalEntity;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setRecipientLegalEntity(LegalEntity recipientLegalEntity) {
        this.recipientLegalEntity = recipientLegalEntity;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public void setPaymentOrderStatus(PaymentOrderStatus paymentOrderStatus) {
        this.paymentOrderStatus = paymentOrderStatus;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public PaymentOrder create() {
        checkOrder();
        PaymentOrder paymentOrder = new PaymentOrder();
//        paymentOrder
        paymentOrder.setCurrencyCode(senderAccount.getCurrencyCode());
        paymentOrder.setSenderAccount(senderAccount);
        paymentOrder.setContract(contract);
        paymentOrder.setRecipientAccount(recipientAccount);
        paymentOrder.setSenderLegalEntity(senderLegalEntity);
        paymentOrder.setRecipientLegalEntity(recipientLegalEntity);
        paymentOrder.setDate(Date.valueOf(LocalDate.now()));
        paymentOrder.setOrderAmount(new BigDecimal(orderAmount));
        paymentOrder.setReason(reason);
        paymentOrder.setPriorityCode("03");
        paymentOrder.setNum(paymentOrderService.findLastNumBySender(paymentOrder.getSenderLegalEntity().getId())+1);
        paymentOrder.setStatus(cache.getPaymentStatusById(2));
        return paymentOrder;
    }

    public void reset(){
        reason=null;
        orderAmount=null;
        senderAccount=null;
        recipientAccount=null;
        recipientLegalEntity=null;
        contract=null;
    }

    private void checkOrder(){
        StringBuilder errors = new StringBuilder("");
        if (senderAccount==null){
            errors.append("Юр. лицо отправителя не выбран. \n");
        }
        if (recipientLegalEntity==null){
            errors.append("Юр. лицо получателя не выбран. \n");
        }
        if (recipientAccount==null) {
            errors.append("Счет получателя не выбран. \n");
        }
        if (!errors.toString().equals("")){
            throw new InvalidOrderException(errors.toString());
        }
        if (!checkCurrencyIdentity()){
            errors.append("Код валюты в контракте, счете отправителя и счете получателя должен совпадать. \n");
        }
        try {
            BigDecimal bigDecimal = new BigDecimal(orderAmount);
            if (bigDecimal.compareTo(new BigDecimal(0))<=0) {
                throw new NumberFormatException();
            }
        }catch (NumberFormatException e){
            errors.append("Неверный формат суммы. ");
        }
        if (!errors.toString().equals("")){
            throw new InvalidOrderException(errors.toString());
        }
    }


    private boolean checkCurrencyIdentity(){
        return recipientAccount.getCurrencyCode().equals(senderAccount.getCurrencyCode()) &&
                (contract!=null ? senderAccount.getCurrencyCode().equals(contract.getCurrencyCode()): true);
    }

    @Override
    public String toString() {
        return "PaymentOrderCreator{" +
                ", num=" + num +
                ", date=" + date +
                ", senderLegalEntity=" + senderLegalEntity +
                ", senderAccount=" + senderAccount +
                ", recipientLegalEntity=" + recipientLegalEntity +
                ", recipientAccount=" + recipientAccount +
                ", contract=" + contract +
                ", currencyCode='" + currencyCode + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", reason='" + reason + '\'' +
                ", priorityCode='" + priorityCode + '\'' +
                ", paymentOrderStatus=" + paymentOrderStatus +
                ", rejectReason='" + rejectReason + '\'' +
                '}';
    }

    public LegalEntity getSenderLegalEntity() {
        return senderLegalEntity;
    }

    public LegalEntity getRecipientLegalEntity() {
        return recipientLegalEntity;
    }
}
