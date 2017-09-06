package ru.atc.bclient.model.service;

import org.springframework.stereotype.Service;
import ru.atc.bclient.model.entities.dim.User;
@Service
public interface UserService {
    void save(User user);
    User getByLogin(String login);
    User getByLoginWithLegalEntities(String login);
}
