package com.miron.directservice.infrastructure;

import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.repository.BasicChatInMemoryRepository;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.User;
import com.miron.directservice.infrastructure.config.DomainConfiguration;
import com.miron.directservice.infrastructure.controller.BasicChatController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BasicChatController.class)
@Import(DomainConfiguration.class)
public class PersonalChatBasicTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChatRepository<PersonalChat> chatRepository;

    @BeforeEach
    void setUp() {
        PersonalChat personalChat = new PersonalChat(
                new ChatName("ChatName"),
                new ArrayList<>(),
                new User(1, "danya", "", ""),
                new User(2, "danya", "", "")
        );
        chatRepository.save(personalChat);
        personalChat = new PersonalChat(
                new ChatName("NewChatName"),
                new ArrayList<>(),
                new User(1, "danya", "", ""),
                new User(2, "danya", "", "")
        );
        chatRepository.save(personalChat);
    }

    @Test
    void getAllChats() throws Exception {
        var mock = mockMvc.perform(
                        get("/api/v1/direct")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }


    @TestConfiguration
    @ComponentScan(
            basePackageClasses = {BasicChatInMemoryRepository.class},
            includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainRepository.class})})
    static class StubConfiguration {
    }

}
