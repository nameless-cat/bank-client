package ru.atc.bclient.web.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.atc.bclient.model.service.AccountBalanceService;

import ru.atc.bclient.web.controller.entities.BalanceDto;

@RestController
public class LegalEntitiesAjaxController {

  @Autowired
  private AccountBalanceService accountBalanceService;

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

}
