package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;
import ru.atc.bclient.model.entities.fct.PaymentOrder;
import ru.atc.bclient.model.repository.PaymentOrderRepository;

import java.sql.Date;
import java.util.List;

@Transactional
public interface JpaPaymentOrderRepository extends JpaRepository<PaymentOrder, Integer>, PaymentOrderRepository {

    @Query("SELECT po.num FROM PaymentOrder po WHERE po.senderLegalEntity.id=?1 and po.num = " +
            "(SELECT max (po2.num) FROM PaymentOrder po2 where po2.senderLegalEntity.id=?1)")
    Integer findLastNumBySender(int SenderLegalEntityId);

    @Query("SELECT po FROM PaymentOrder po WHERE po.date>=?2 and po.date<=?3 and po.senderLegalEntity.id=?1")
    List<PaymentOrder> getBetweenDateBySenderLegalEntityId(int senderLegalEntityId, Date dateBegin, Date dateEnd);

    @Modifying
    @Query("DELETE FROM PaymentOrder po WHERE po.id=?1 AND po.senderLegalEntity.id=?2")
    int delete(int id, int legalEntityId);

    @Query("select po FROM PaymentOrder po WHERE po.status.id=1 or po.status.id=2 ORDER BY po.status.code asc, po.date desc     ")
    List<PaymentOrder> getAllNotHandled();

    @Modifying
    @Query("UPDATE PaymentOrder po SET po.status =?3 WHERE po.id=?1 AND po.senderLegalEntity.id=?2")
    int updateStatus(int id, int legalEntityId, PaymentOrderStatus status);


}
