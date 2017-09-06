package ru.atc.bclient.web.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "aivanov", password = "ivanov", authorities = "USER")
public class LegalEntitiesAjaxControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetBalance() throws Exception {
    mockMvc.perform(get("/manage/account/balance")
        .param("accountId", "1"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}