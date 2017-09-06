package ru.atc.bclient.model.service;

import ru.atc.bclient.model.entities.dim.Bank;

public interface BankService {
    Bank getById(int id);
}
