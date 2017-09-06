package repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.atc.bclient.model.repository.ContractRepository;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestContractRepository {
    @Autowired
    ContractRepository contractRepository;

    @Test
    public void testGetContractsByIssuerId(){
        System.out.println(contractRepository.getByIssuerLegalEntityId(1));
    }
}
