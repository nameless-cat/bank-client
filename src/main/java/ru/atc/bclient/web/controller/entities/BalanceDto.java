package ru.atc.bclient.web.controller.entities;

import lombok.Getter;
import lombok.Setter;
import ru.atc.bclient.model.entities.fct.AccountBalance;

@Getter
@Setter
public class BalanceDto {
  private AccountBalance currentBalance;
  private AccountBalance balanceByDate;

}
