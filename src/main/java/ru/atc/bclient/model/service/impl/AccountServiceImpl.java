package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.Account;
import ru.atc.bclient.model.entities.dim.AccountStatus;
import ru.atc.bclient.model.repository.AccountRepository;
import ru.atc.bclient.model.service.AccountService;
import ru.atc.bclient.model.service.AccountStatusService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountStatusService accountStatusService;
    @Override
    public Account getById(int id) {
        return accountRepository.findOne(id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAllByCurrencyCode(String currencyCode) {
        return accountRepository.findAllByCurrencyCode(currencyCode);
    }

    @Override
    public List<Account> getAllByLegalEntityId(int id) {
        return accountRepository.findAllByLegalEntityId(id);
    }

    @Override
    public List<Account> findAllByLegalEntityIdWithBanks(int id) {
        return accountRepository.findAllByLegalEntityId(id);
    }

    @Override
    public List<Account> getAllActiveByLegalEntityId(int id) {
        return accountRepository.getAllByLegalEntityIdAndStatus(id, accountStatusService.getById(1));
    }
}
