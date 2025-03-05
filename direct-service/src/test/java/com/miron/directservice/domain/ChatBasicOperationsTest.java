package com.miron.directservice.domain;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.repository.GroupChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.repository.PersonalChatInMemoryRepository;
import com.miron.directservice.domain.service.GroupChatBasicService;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.service.MessageService;
import com.miron.directservice.domain.service.PersonalChatBasicService;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.valueObject.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatBasicOperationsTest {
    List<Message> messages = new ArrayList<>();
    ChatBasicService<PersonalChat> personalChatBasicService;
    MessageBasicService chatMessageBasicService;
    ChatRepository<PersonalChat> personalChatRepository = new PersonalChatInMemoryRepository();

    ChatBasicService<GroupChat> groupChatBasicService;
    ChatRepository<GroupChat> groupChatRepository = new GroupChatInMemoryRepository();
    MessageRepository messageRepository = new MessagesInMemoryRepository();

    PersonalChat personalChat;
    GroupChat groupChat;

//    @BeforeEach
//    public void setUp() {
//        messages = RandomMessagesCreation.createRandomMessages(10);
//        chatMessageBasicService = new MessageService(messageRepository);
//        personalChatBasicService = new PersonalChatBasicService(personalChatRepository, chatMessageBasicService);
//        groupChatBasicService = new GroupChatBasicService(groupChatRepository, chatMessageBasicService);
//
//        personalChat = new PersonalChat(
//                new ChatName("ChatName"),
//                new User(1, "danya", "", ""),
//                new User(2, "danya", "", "")
//        );
//        groupChat = new GroupChat(
//                new ChatName("ChatName")
//        );
//        groupChatBasicService.createChat(groupChat);
//        personalChatBasicService.createChat(personalChat);
//    }

    @Test
    public void testDeletingFunctionality() {
        personalChatBasicService.addMessage(messages.get(0), personalChat.getId());
        groupChatBasicService.addMessage(messages.get(1), groupChat.getId());

        assertThat(groupChatBasicService.deleteMessage(messages.get(0), groupChat.getId())).isEqualTo(null); //deleting message from another chat

        groupChatBasicService.deleteMessage(messages.get(1), groupChat.getId());
        assertThat(groupChat.getMessages()).isEmpty();
    }
}
