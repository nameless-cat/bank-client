package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.atc.bclient.model.entities.fct.AccountBalance;
import ru.atc.bclient.model.repository.AccountBalanceRepository;

import java.sql.Date;
import java.util.List;

public interface JpaAccountBalanceRepository extends JpaRepository<AccountBalance, Integer>, AccountBalanceRepository {
    @Query("SELECT ab FROM AccountBalance ab WHERE ab.account.id=?1 and ab.date = " +
            "(SELECT max (ab2.date) FROM AccountBalance ab2 where ab2.account.id=?1)")
    AccountBalance getNewestByAccountId(int id);

    @Query("SELECT ab FROM AccountBalance ab WHERE ab.account.id=?1")
    List<AccountBalance> getByAccountId(int accountId);

    @Query("SELECT ab FROM AccountBalance ab WHERE ab.id=?1")
    AccountBalance getById(int id);

    @Query("SELECT ab FROM AccountBalance ab WHERE ab.account.id=?1 and ab.date =(select MAX (ab2.date) FROM AccountBalance ab2 where ab2.date<=?2 and ab2.account.id=?1)")
    AccountBalance getByAccountAndDate(int AccountId, Date date);

    AccountBalance save(AccountBalance accountBalance);
}
