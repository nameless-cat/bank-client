package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.atc.bclient.model.entities.dim.User;
import ru.atc.bclient.model.repository.UserRepository;

@Transactional(readOnly = true)
public interface JpaUserRepository extends JpaRepository<User, Integer>, UserRepository {
    User getByLogin(String login);

    @EntityGraph(attributePaths={"legalEntities"})
    @Query("SELECT u FROM User u WHERE u.login=?1")
    User getByLoginWithLegalEntities(String login);

    User getById(int id);

    User save(User user);


}
