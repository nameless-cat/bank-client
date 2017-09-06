package ru.atc.bclient.model.service;

import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;

public interface PaymentOrderStatusService {
    PaymentOrderStatus getById(int id);
}
