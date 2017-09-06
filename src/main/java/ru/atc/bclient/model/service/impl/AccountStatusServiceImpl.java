package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.AccountStatus;
import ru.atc.bclient.model.entities.fct.AccountBalance;
import ru.atc.bclient.model.repository.AccountStatusRepository;
import ru.atc.bclient.model.service.AccountBalanceService;
import ru.atc.bclient.model.service.AccountStatusService;

import java.sql.Date;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {
    @Autowired
    AccountStatusRepository accountStatusRepository;
    @Override
    public AccountStatus getById(int id) {
        return accountStatusRepository.findOne(id);
    }
}
