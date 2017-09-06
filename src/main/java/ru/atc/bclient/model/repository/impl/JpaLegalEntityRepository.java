package ru.atc.bclient.model.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.repository.LegalEntityRepository;

import java.util.List;

@Repository
public interface JpaLegalEntityRepository extends JpaRepository<LegalEntity, Integer>, LegalEntityRepository {

    //С проверкой пользователя (Почему-то не работает)
    @EntityGraph(attributePaths = "accounts", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT le FROM LegalEntity le WHERE le.id=?2 AND le in (SELECT u.legalEntities FROM User u WHERE u.id=?1)")
    LegalEntity getByIdWithAccounts(int userId, int id);

    @EntityGraph(attributePaths = "accounts")
    @Query("SELECT le FROM LegalEntity le WHERE le.id=?1")
    LegalEntity getByIdWithAccounts(int id);

    List<LegalEntity> findAll();

    LegalEntity getById(int id);


}
