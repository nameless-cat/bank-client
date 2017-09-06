package ru.atc.bclient.model.service;

import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;
import ru.atc.bclient.model.entities.fct.PaymentOrder;

import java.sql.Date;
import java.util.List;


public interface PaymentOrderService {
    boolean isAvailable();

    int findLastNumBySender(int SenderLegalEntityId);

    PaymentOrder save(PaymentOrder paymentOrder);

    List<PaymentOrder> getBetweenDateBySenderLegalEntityId(int senderLegalEntityId, Date dateBegin, Date dateEnd);

    boolean delete(int id, int legalEntityId);

    List<PaymentOrder> getAllNotHandled();

    boolean cancel(int id, int legalEntityId);
}
