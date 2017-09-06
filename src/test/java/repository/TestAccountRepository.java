package repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.atc.bclient.model.repository.AccountRepository;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAccountRepository {
    @Autowired
    AccountRepository accountRepository;
    @Test
    public void testGetAll(){
        System.out.println(accountRepository.findOne(1).getBank().getId());
        System.out.println(accountRepository.findOne(1).getBank().getCorrAcc());
        //Assert.assertNotEquals(null, accountRepository.findAll());
    }

    @Test
    public void testFindAllByCurrencyCode(){
        System.out.println(accountRepository.findAllByCurrencyCode("840"));

    }
}
