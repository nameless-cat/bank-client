package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.fct.Operation;
import ru.atc.bclient.model.repository.OperationRepository;
import ru.atc.bclient.model.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService{
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Operation getById(int id) {
        return operationRepository.getById(id);
    }
    @Override
    public Operation save(Operation operation){
        return operationRepository.save(operation);
    }
}
