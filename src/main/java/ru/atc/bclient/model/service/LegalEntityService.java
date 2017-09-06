package ru.atc.bclient.model.service;

import ru.atc.bclient.model.entities.dim.LegalEntity;

import java.util.List;

public interface LegalEntityService {
    //LegalEntity getById(int id);
    LegalEntity getByIdWithAccounts(int id);
    LegalEntity getById(int id);
    LegalEntity getByIdWithAccounts(int userId, int id);
    List<LegalEntity> findAll();


}
