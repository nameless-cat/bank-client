package ru.atc.bclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.atc.bclient.model.PaymentOrderHandler;
import ru.atc.bclient.model.entities.fct.PaymentOrder;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {
    @Autowired
    PaymentOrderHandler paymentOrderHandler;

    @Scheduled(cron="0 0 15 * * *", zone="Europe/Istanbul")
    public void scheduledTask() {
        paymentOrderHandler.handle();
    }


}
