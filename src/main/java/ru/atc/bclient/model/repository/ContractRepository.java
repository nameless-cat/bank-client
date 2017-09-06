package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.dim.Contract;

import java.util.List;

public interface ContractRepository {
    List<Contract> getByIssuerLegalEntityId(int id);

    Contract findOne(int id);

    List<Contract> getBySingerLegalEntityId(int id);
}
