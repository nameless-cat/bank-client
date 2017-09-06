package repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.repository.UserRepository;
import ru.atc.bclient.model.repository.impl.JpaLegalEntityRepository;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestLegalEntityRepository {
    @Autowired
    private JpaLegalEntityRepository jpaLegalEntityRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testGetAllByUserId(){

        System.out.println(userRepository.getByLogin("aivanov").getLegalEntities());
        //jpaLegalEntityRepository.getByIdWithAccounts(1, 1);
    }
}
