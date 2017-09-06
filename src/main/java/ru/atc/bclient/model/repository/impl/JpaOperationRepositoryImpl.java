package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.atc.bclient.model.entities.fct.Operation;
import ru.atc.bclient.model.repository.OperationRepository;

public interface JpaOperationRepositoryImpl extends JpaRepository<Operation, Integer>, OperationRepository{
}
