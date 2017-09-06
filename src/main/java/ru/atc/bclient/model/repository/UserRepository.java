package ru.atc.bclient.model.repository;

import ru.atc.bclient.model.entities.dim.User;

public interface UserRepository {
    User getByLogin(String login);
    User save(User user);
    User getByLoginWithLegalEntities(String login);
    User getById(int id);
}
