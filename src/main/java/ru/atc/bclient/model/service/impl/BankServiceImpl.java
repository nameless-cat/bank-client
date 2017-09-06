package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.Bank;
import ru.atc.bclient.model.repository.BankRepository;
import ru.atc.bclient.model.service.BankService;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    BankRepository bankRepository;

    @Override
    public Bank getById(int id) {
        return bankRepository.getById(id);
    }
}
