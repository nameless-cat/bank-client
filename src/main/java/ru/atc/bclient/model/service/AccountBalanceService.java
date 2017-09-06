package ru.atc.bclient.model.service;

import ru.atc.bclient.model.entities.fct.AccountBalance;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface AccountBalanceService {
    AccountBalance getNewestByAccountId(int id);
    AccountBalance getByAccountAndDate(int accountId, Date date);

    AccountBalance save(AccountBalance accountBalance);
}

