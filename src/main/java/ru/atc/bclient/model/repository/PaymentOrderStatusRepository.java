package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;

public interface PaymentOrderStatusRepository {
    PaymentOrderStatus findOne(int id);
}
