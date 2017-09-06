package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;
import ru.atc.bclient.model.repository.PaymentOrderStatusRepository;

public interface JpaPaymentOrderStatusRepository extends JpaRepository<PaymentOrderStatus, Integer>, PaymentOrderStatusRepository {
    PaymentOrderStatus findOne(int id);
}
