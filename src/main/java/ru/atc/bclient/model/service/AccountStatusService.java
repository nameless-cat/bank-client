package ru.atc.bclient.model.service;

import ru.atc.bclient.model.entities.dim.AccountStatus;

public interface AccountStatusService {
    AccountStatus getById(int id);
}
