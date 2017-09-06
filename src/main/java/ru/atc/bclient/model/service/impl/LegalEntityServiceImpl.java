package ru.atc.bclient.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.repository.LegalEntityRepository;
import ru.atc.bclient.model.service.LegalEntityService;

import java.util.List;

@Service
public class LegalEntityServiceImpl implements LegalEntityService {
    @Autowired
    private LegalEntityRepository repository;



    @Override
    public LegalEntity getByIdWithAccounts(int id){
        return repository.getByIdWithAccounts(id);
    }

    @Override
    public LegalEntity getById(int id) {
        return repository.getById(id);
    }

    @Override
    public LegalEntity getByIdWithAccounts(int userId, int id) {
        return repository.getByIdWithAccounts(userId, id);
    }

    @Override
    public List<LegalEntity> findAll() {
        return repository.findAll();
    }
}
