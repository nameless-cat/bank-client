package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.fct.AccountBalance;
import ru.atc.bclient.model.repository.AccountBalanceRepository;
import ru.atc.bclient.model.service.AccountBalanceService;

import java.sql.Date;

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService{

    @Autowired
    AccountBalanceRepository accountBalanceRepository;

    @Override
    public AccountBalance getNewestByAccountId(int id) {
        return accountBalanceRepository.getNewestByAccountId(id);
    }

    @Override
    public AccountBalance getByAccountAndDate(int accountId, Date date) {
        return accountBalanceRepository.getByAccountAndDate(accountId, date);
    }

    @Override
    public AccountBalance save(AccountBalance accountBalance) {
        return accountBalanceRepository.save(accountBalance);
    }


}
