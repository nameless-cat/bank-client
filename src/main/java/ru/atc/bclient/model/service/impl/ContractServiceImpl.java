package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.Contract;
import ru.atc.bclient.model.repository.ContractRepository;
import ru.atc.bclient.model.service.ContractService;

import java.util.List;
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Override
    public List<Contract> getByIssuerId(int id) {
        return contractRepository.getByIssuerLegalEntityId(id);
    }

    @Override
    public List<Contract> getBySingerId(int id) {
        return contractRepository.getBySingerLegalEntityId(id);
    }

    @Override
    public Contract getOne(int id) {
        return contractRepository.findOne(id);
    }


}
