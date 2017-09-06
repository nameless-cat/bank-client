package ru.atc.bclient.model.service;

import ru.atc.bclient.model.entities.fct.Operation;

public interface OperationService {
    Operation getById(int id);
    Operation save(Operation operation);
}
