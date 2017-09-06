package ru.atc.bclient.model.service;

import ru.atc.bclient.model.entities.dim.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> getByIssuerId(int id);
    List<Contract> getBySingerId(int id);
    Contract getOne(int id);
}
