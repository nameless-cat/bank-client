package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.atc.bclient.model.entities.dim.Bank;
import ru.atc.bclient.model.repository.BankRepository;

public interface JpaBankRepository extends JpaRepository<Bank, Integer>, BankRepository {
    Bank getById(int id);
}
