package ru.atc.bclient.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;
import ru.atc.bclient.model.entities.fct.PaymentOrder;

import java.sql.Date;
import java.util.List;

public interface PaymentOrderRepository {
    Integer findLastNumBySender(int SenderLegalEntityId);
    PaymentOrder save(PaymentOrder paymentOrder);
    List<PaymentOrder> getBetweenDateBySenderLegalEntityId(int senderLegalEntityId, Date dateBegin, Date dateEnd);
    int delete(int id, int legalEntityId);
    List<PaymentOrder> getAllNotHandled();
    int updateStatus(int id, int legalEntityId, PaymentOrderStatus status);


}
