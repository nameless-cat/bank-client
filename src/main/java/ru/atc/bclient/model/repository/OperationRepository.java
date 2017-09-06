package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.fct.Operation;

public interface OperationRepository {
    Operation getById(int id);
    Operation save(Operation operation);
}
