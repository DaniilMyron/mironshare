package com.miron.directservice.domain;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.repository.PersonalChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.service.PersonalChatService;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.service.MessageService;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.Message;
import com.miron.directservice.domain.valueObject.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatBasicOperationsTest {
    List<Message> messages = new ArrayList<>();
    ChatBasicService<PersonalChat> chatBasicService;
    MessageBasicService messageBasicService;
    ChatRepository<PersonalChat> chatRepository = new PersonalChatInMemoryRepository();
    MessageRepository messageRepository = new MessagesInMemoryRepository(chatRepository);
    @BeforeEach
    public void setUp() {
        messages = RandomMessagesCreation.createRandomMessages(10);
        messageBasicService = new MessageService(messageRepository);
        chatBasicService = new PersonalChatService(chatRepository, messageBasicService);
    }

    @Test
    public void test() {
        PersonalChat chat = new PersonalChat(
                new ChatName("ChatName"),
                new ArrayList<>(),
                new User("danya", "", ""),
                new User("danya", "", "")
        );
        chatBasicService.createChat(chat);
        var chatWithMessage = chatBasicService.addMessage(messages.getFirst(), chat.getId());
        System.out.println(chatWithMessage.getMessages().getFirst().getValue());
        assertThat(chat).isEqualTo(chatWithMessage);
        assertThat(chat).isEqualTo(chatRepository.findById(chat.getId()));
    }
}
