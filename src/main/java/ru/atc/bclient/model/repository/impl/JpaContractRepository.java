package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.atc.bclient.model.entities.dim.Contract;
import ru.atc.bclient.model.repository.ContractRepository;

import java.util.List;

public interface JpaContractRepository extends JpaRepository<Contract, Integer>, ContractRepository {

    @Query("SELECT c FROM Contract c WHERE c.issuerLegalEntity.id=?1")
    List<Contract> getByIssuerLegalEntityId(int id);

    List<Contract> getBySingerLegalEntityId(int id);
}
