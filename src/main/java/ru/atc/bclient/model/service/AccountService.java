package ru.atc.bclient.model.service;

import org.springframework.data.jpa.repository.Query;
import ru.atc.bclient.model.entities.dim.Account;

import java.util.List;

public interface AccountService {

    Account getById(int id);


    List<Account> findAll();

    List<Account> findAllByCurrencyCode(String currencyCode);

    List<Account> getAllByLegalEntityId(int id);
    List<Account> findAllByLegalEntityIdWithBanks(int id);

    List<Account> getAllActiveByLegalEntityId(int id);
}
