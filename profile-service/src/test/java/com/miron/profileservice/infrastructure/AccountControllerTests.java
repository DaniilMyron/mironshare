package com.miron.profileservice.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.infrastructure.config.DomainBeansCreationConfig;
import com.miron.profileservice.infrastructure.controller.model.AccountResponse;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(DomainBeansCreationConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTests {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String BASE_URL = "/api/v1/profile";
    @MockitoBean
    private AccountService<Account> accountService;
    @Autowired
    private TestRestTemplate testRestTemplate;

    private Account account = new Account("username", "password1234", "danya", new TestEncoder());

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void retrieve_profile_by_id(){
        when(accountService.retrieveUser(account.getId())).thenReturn(account);

        AccountResponse template = testRestTemplate.getForObject(BASE_URL + "/" + account.getId(), AccountResponse.class);

        assertThat(template).isEqualTo(new AccountResponse(account));
    }


    @Test
    public void retrieve_profiles_by_ids() throws Exception {
        List<UUID> uuids = List.of(account.getId());
        when(accountService.retrieveUsers(uuids)).thenReturn(List.of(account));

        String firstValue = "{\"userId\":\"" + account.getId() + "\"}";
        mockMvc.perform(get("/api/v1/profile/retrieve-profiles")
                        .content(firstValue))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    private static class TestEncoder implements BCryptEncoderForAccountPassword {
        @Override
        public String encode(String password) {
            return password + " test encoder";
        }
    }
}
