package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;
import ru.atc.bclient.model.repository.PaymentOrderStatusRepository;
import ru.atc.bclient.model.service.PaymentOrderStatusService;

@Service
public class PaymentOrderStatusServiceImpl implements PaymentOrderStatusService {

    @Autowired
    PaymentOrderStatusRepository paymentOrderStatusRepository;

    @Override
    public PaymentOrderStatus getById(int id) {
        return paymentOrderStatusRepository.findOne(id);
    }
}
