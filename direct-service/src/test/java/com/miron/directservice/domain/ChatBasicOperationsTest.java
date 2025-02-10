package com.miron.directservice.domain;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.repository.ChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.service.ChatService;
import com.miron.directservice.domain.service.MessageService;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatBasicOperationsTest {
    List<Message> messages = new ArrayList<>();
    ChatBasicService chatBasicService;
    ChatRepository chatRepository = new ChatInMemoryRepository();
    @BeforeEach
    public void setUp() {
        messages = RandomMessagesCreation.createRandomMessages(10);
        chatBasicService = new ChatService(chatRepository, new MessageService(new MessagesInMemoryRepository(chatRepository)));
    }

    @Test
    public void test() {
        Chat chat = new Chat(new ChatName("ChatName"), new ArrayList<>());
        chatBasicService.createChat(chat);
        var chatWithMessage = chatBasicService.addMessage(messages.getFirst(), chat.getId());
        assertThat(chat).isEqualTo(chatWithMessage);
        assertThat(chat).isEqualTo(chatRepository.findById(chat.getId()));
    }
}
