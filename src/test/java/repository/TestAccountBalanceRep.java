package repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.atc.bclient.model.repository.AccountBalanceRepository;
import ru.atc.bclient.model.repository.impl.JpaAccountBalanceRepository;

import java.sql.Date;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAccountBalanceRep {
    @Autowired
    AccountBalanceRepository accountBalanceRepository;

    @Autowired
    JpaAccountBalanceRepository jpaAccountBalanceRepository;
    @Test
    public void testGetById(){
        System.out.println(jpaAccountBalanceRepository.getById(101));
    }

    @Test
    public void testGetNewest(){
        System.out.println(jpaAccountBalanceRepository.getNewestByAccountId(1));
    }

    @Test
    public void testGetBy(){
        System.out.println(jpaAccountBalanceRepository.getByAccountAndDate(1, Date.valueOf("2017-08-07")));
    }
}
