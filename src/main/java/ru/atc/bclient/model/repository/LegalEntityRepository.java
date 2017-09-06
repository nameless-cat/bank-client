package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.dim.LegalEntity;

import java.util.List;

public interface LegalEntityRepository {
    LegalEntity getByIdWithAccounts(int userId, int id);
    LegalEntity getByIdWithAccounts(int id);

    List<LegalEntity> findAll();
    LegalEntity getById(int id);
}
