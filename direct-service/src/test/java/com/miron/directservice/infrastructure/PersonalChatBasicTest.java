package com.miron.directservice.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.repository.GroupChatInMemoryRepository;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.valueObject.User;
import com.miron.directservice.infrastructure.config.DomainConfiguration;
import com.miron.directservice.infrastructure.controller.BasicChatController;
import com.miron.directservice.infrastructure.controller.model.MessageIdRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BasicChatController.class)
@Import(DomainConfiguration.class)
public class PersonalChatBasicTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String BASE_URL = "/api/v1/direct";

    @Autowired
    private ChatBasicService<PersonalChat> personalChatBasicService;

    @Autowired
    private ChatRepository<PersonalChat> chatRepository;

    /*
    @BeforeEach
    void setUp() {
        PersonalChat personalChat = new PersonalChat(
                new ChatName("ChatName"),
                new User(1, "danya", "", ""),
                new User(2, "danya", "", "")
        );
        personalChatBasicService.createChat(personalChat);
        personalChatBasicService.addMessage(new Message("firstMessage firstChat", 1), personalChat.getId());
        personalChatBasicService.addMessage(new Message("secondMessage firstChat", 2), personalChat.getId());
        personalChat = new PersonalChat(
                new ChatName("NewChatName"),
                new User(1, "danya", "", ""),
                new User(2, "danya", "", "")
        );
        personalChatBasicService.createChat(personalChat);
        personalChatBasicService.addMessage(new Message("firstMessage secondChat", 1), personalChat.getId());
        personalChatBasicService.addMessage(new Message("secondMessage secondChat", 2), personalChat.getId());
    }
     */

    @Test
    void getAllChats() throws Exception {
        var mock = mockMvc.perform(
                        get(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @Test
    void getChatById() throws Exception {
        var mock = mockMvc.perform(
                        get(BASE_URL + "/%s".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
        mock = mockMvc.perform(
                        get(BASE_URL + "/%s".formatted(chatRepository.findAll().getLast().getId()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @Test
    void findMessageByChatId() throws Exception {
        var mock = mockMvc.perform(
                        get(BASE_URL + "/%s/find-message".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"text\":\"first\"}"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
        mock = mockMvc.perform(
                        get(BASE_URL + "/%s/find-message".formatted(chatRepository.findAll().getLast().getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"text\":\"first\"}"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @Test
    void redactMessageByChatId() throws Exception {
        var message = chatRepository.findAll().getFirst().getMessages().getFirst();
        String firstValue = objectMapper.writeValueAsString(new MessageIdRequest(message.getId(), "A new Text for message"));
        message = chatRepository.findAll().getLast().getMessages().getFirst();
        String secondValue = objectMapper.writeValueAsString(new MessageIdRequest(message.getId(), "A new Text for message"));
        var mock = mockMvc.perform(
                        patch(BASE_URL + "/%s/redact".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(firstValue))
                .andExpect(status().isOk())
                .andReturn();
        //System.out.println(firstValue + " first Value");
        System.out.println(mock.getResponse().getContentAsString());
        mock = mockMvc.perform(
                        patch(BASE_URL + "/%s/redact".formatted(chatRepository.findAll().getLast().getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(secondValue))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @Test
    void createPersonalChat() throws Exception {
        var mock = mockMvc.perform(
                        post(BASE_URL + "/create-personal-chat/%s".formatted("4a88cec9-7b5d-4bbd-af22-37a4866afa97"))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @Test
    void sendMessageToChat() throws Exception {
        var mock = mockMvc.perform(
                        post(BASE_URL + "/%s/send".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"text\":\"Hi!\"}"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
        mock = mockMvc.perform(
                        get(BASE_URL + "/%s".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @Test
    void clearChatMessages() throws Exception {
        var mock = mockMvc.perform(
                        delete(BASE_URL + "/%s".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
        mock = mockMvc.perform(
                        delete(BASE_URL + "/%s".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @Test
    void deleteMessageInChat() throws Exception {
        var message = chatRepository.findAll().getFirst().getMessages().getFirst();
        String firstValue = objectMapper.writeValueAsString(new MessageIdRequest(message.getId(), "A new Text for message"));
        message = chatRepository.findAll().getLast().getMessages().getFirst();
        String secondValue = objectMapper.writeValueAsString(new MessageIdRequest(message.getId(), "A new Text for message"));
        var mock = mockMvc.perform(
                        delete(BASE_URL + "/%s/delete".formatted(chatRepository.findAll().getFirst().getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(firstValue))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
        mock = mockMvc.perform(
                        delete(BASE_URL + "/%s/delete".formatted(chatRepository.findAll().getLast().getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(secondValue))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();
        System.out.println(mock.getResponse().getContentAsString());
    }

    @TestConfiguration
    @ComponentScan(
            basePackageClasses = {GroupChatInMemoryRepository.class},
            includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainRepository.class})})
    static class StubConfiguration {
    }

    @TestConfiguration
    static class BeanConfiguration {
        @Bean
        public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
            return args -> {

                System.out.println("Let's inspect the beans provided by Spring Boot:");

                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
            };
        }
    }
}
