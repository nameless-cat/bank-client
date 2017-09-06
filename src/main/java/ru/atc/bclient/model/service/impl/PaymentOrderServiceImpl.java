package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.PaymentOrderHandler;
import ru.atc.bclient.model.entities.fct.PaymentOrder;
import ru.atc.bclient.model.repository.PaymentOrderRepository;
import ru.atc.bclient.model.service.PaymentOrderService;
import ru.atc.bclient.model.service.PaymentOrderStatusService;

import java.sql.Date;
import java.util.List;

@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {
    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private PaymentOrderStatusService paymentOrderStatusService;

    @Autowired
    private PaymentOrderHandler paymentOrderHandler;




    public int findLastNumBySender(int senderLegalEntityId) {
        Integer num = paymentOrderRepository.findLastNumBySender(senderLegalEntityId);
        return num != null ? num : 1;
    }

    @Override
    public boolean delete(int id, int legalEntityId) {
        return paymentOrderRepository.delete(id, legalEntityId) > 0;
    }

    @Override
    public List<PaymentOrder> getAllNotHandled() {
        return paymentOrderRepository.getAllNotHandled();
    }

    @Override
    public boolean cancel(int id, int legalEntityId) {

        return paymentOrderRepository.updateStatus(id, legalEntityId, paymentOrderStatusService.getById(3)) > 0;

    }

    @Override
    public PaymentOrder save(PaymentOrder paymentOrder) {
        return paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public List<PaymentOrder> getBetweenDateBySenderLegalEntityId(int senderLegalEntityId, Date dateBegin, Date dateEnd) {
        return paymentOrderRepository.getBetweenDateBySenderLegalEntityId(senderLegalEntityId, dateBegin, dateEnd);
    }

    public boolean isAvailable() {
        return !paymentOrderHandler.isHandling();
    }


}
