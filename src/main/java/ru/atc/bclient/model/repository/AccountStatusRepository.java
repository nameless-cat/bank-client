package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.dim.AccountStatus;

public interface AccountStatusRepository {
    AccountStatus findOne(int id);
}
