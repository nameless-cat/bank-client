package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.atc.bclient.model.entities.dim.Account;
import ru.atc.bclient.model.entities.dim.AccountStatus;
import ru.atc.bclient.model.repository.AccountRepository;

import java.util.List;

public interface JpaAccountRepository extends JpaRepository<Account, Integer>, AccountRepository {
    @Override
    Account findOne(int id);

    @Override
    List<Account> findAll();

    @Query("SELECT a FROM Account a WHERE a.legalEntity.id=?1")
    List<Account> findAllByLegalEntityId(int id);

    List<Account> findAllByCurrencyCode(String currencyCode);

    List<Account> getAllByLegalEntityIdAndStatus(int id, AccountStatus status);
}
