package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.dim.Account;
import ru.atc.bclient.model.entities.dim.AccountStatus;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();
    Account findOne(int id);
    List<Account> findAllByLegalEntityId(int id);

    List<Account> findAllByCurrencyCode(String currencyCode);
    List<Account> getAllByLegalEntityIdAndStatus(int id, AccountStatus status);
}
