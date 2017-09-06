package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.atc.bclient.model.entities.dim.AccountStatus;
import ru.atc.bclient.model.repository.AccountStatusRepository;

public interface JpaAccountStatusRepository extends JpaRepository<AccountStatus, Integer>, AccountStatusRepository{
    AccountStatus findOne(int id);

}
