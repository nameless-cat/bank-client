package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.dim.Bank;

public interface BankRepository {
    Bank getById(int id);
}
