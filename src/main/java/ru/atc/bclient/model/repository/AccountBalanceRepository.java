package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.fct.AccountBalance;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface AccountBalanceRepository {
    AccountBalance getNewestByAccountId(int id);

    AccountBalance getByAccountAndDate(int accountId, Date date);

    List<AccountBalance> getByAccountId(int accountId);

    AccountBalance save(AccountBalance accountBalance);


}
