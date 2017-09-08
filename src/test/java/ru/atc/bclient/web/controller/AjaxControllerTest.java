package ru.atc.bclient.web.controller;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.atc.bclient.CurrentInformation;
import ru.atc.bclient.model.entities.dim.LegalEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "aivanov", password = "ivanov", authorities = "USER")
public class AjaxControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CurrentInformation information;

  @Test
  public void testGetBalance() throws Exception {
    mockMvc.perform(get("/manage/account/balanceWithDate")
        .param("accountId", "1")
        .param("date", "2017-09-07"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void testPaymentsWithDate() throws Exception {

    LegalEntity entity = new LegalEntity();
    entity.setId(2);

    given(information.getLegalEntity()).willReturn(entity);

    mockMvc.perform(get("/manage/paymentsWithDate")
        .param("dateBegin", "2017-07-30")
        .param("dateEnd", "2017-09-10"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}