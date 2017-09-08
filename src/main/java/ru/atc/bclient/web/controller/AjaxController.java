package ru.atc.bclient.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.atc.bclient.CurrentInformation;
import ru.atc.bclient.model.service.AccountBalanceService;

import ru.atc.bclient.model.service.PaymentOrderService;
import ru.atc.bclient.web.controller.entities.BalanceDto;
import ru.atc.bclient.web.controller.entities.PaymentsDto;
import ru.atc.bclient.web.controller.view.View;

@RestController
public class AjaxController {

  @Autowired
  private AccountBalanceService accountBalanceService;

  @Autowired
  private CurrentInformation currentInformation;

  @Autowired
  private PaymentOrderService paymentOrderService;

  @GetMapping(value = "/manage/account/balanceWithDate")
  public BalanceDto getBalanceWithDate(@RequestParam int accountId,
      @RequestParam(value = "date", required = false) Date date) {

    BalanceDto balanceDto = new BalanceDto();
    balanceDto.setCurrentBalance(accountBalanceService.getNewestByAccountId(accountId));
    if (date != null) {
      balanceDto.setBalanceByDate(accountBalanceService.getByAccountAndDate(accountId, date));
    }
    return balanceDto;
  }

  @JsonView(View.PaymentsWithDate.class)
  @GetMapping(value = "/manage/paymentsWithDate")
  public PaymentsDto paymentsWithDate(
      @RequestParam(value = "dateBegin", required = false) Date dateBegin,
      @RequestParam(value = "dateEnd", required = false) Date dateEnd) {

    //todo сделать как в topjava
    if (dateBegin != null
        && dateEnd != null
        && dateBegin.after(dateEnd)) {
      return null;
    }

    PaymentsDto paymentsDto = new PaymentsDto();

    paymentsDto.setPayments(paymentOrderService
        .getBetweenDateBySenderLegalEntityId(currentInformation.getLegalEntity().getId(),
            dateBegin, dateEnd));

    return paymentsDto;
  }


  @PutMapping(value = "/manage/payments/cancel/{paymentOrderId}")
  public void cancelOrder(@PathVariable int paymentOrderId) {
    paymentOrderService.cancel(paymentOrderId, currentInformation.getLegalEntity().getId());
  }

}
