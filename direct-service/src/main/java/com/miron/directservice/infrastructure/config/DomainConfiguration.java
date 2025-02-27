package com.miron.directservice.infrastructure.config;

import com.miron.directservice.domain.api.*;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.fatory.ServicesFactory;
import com.miron.directservice.domain.fatory.ServicesFactoryImpl;
import com.miron.directservice.domain.repository.GroupChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.repository.PersonalChatInMemoryRepository;
import com.miron.directservice.domain.service.GroupChatBasicService;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.service.MessageService;
import com.miron.directservice.domain.service.PersonalChatBasicService;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.usecases.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
public class DomainConfiguration {
    @Bean
    public MessageRepository messageRepository() {
        return new MessagesInMemoryRepository();
    }

    @Bean
    public ChatRepository<PersonalChat> personalChatInMemoryRepository() {
        return new PersonalChatInMemoryRepository();
    }

    @Bean
    public ChatRepository<GroupChat> groupChatInMemoryRepository() {
        return new GroupChatInMemoryRepository();
    }

    @Bean
    public MessageBasicService messageBasicService(MessageRepository messageRepository) {
        return new MessageService(messageRepository);
    }

    @Bean
    public ChatBasicService<PersonalChat> personalChatBasicService(ChatRepository<PersonalChat> personalChatRepository, MessageBasicService messageBasicService) {
        return new PersonalChatBasicService(personalChatRepository, messageBasicService);
    }

    @Bean
    public ChatBasicService<GroupChat> groupChatBasicService(ChatRepository<GroupChat> groupChatRepository, MessageBasicService messageBasicService) {
        return new GroupChatBasicService(groupChatRepository, messageBasicService);
    }

    @Bean
    public ServicesFactory servicesFactory(ChatBasicService<PersonalChat> personalChat, ChatBasicService<GroupChat> groupChat){
        return new ServicesFactoryImpl(personalChat, groupChat);
    }

    @Bean
    public RetrieveChats retrieveChatsCommand(ServicesFactory servicesFactory){
        return new RetrieveAllChatsUseCase(servicesFactory);
    }

    @Bean
    @Qualifier("retrieveAnyChat")
    public RetrieveChat retrieveAnyChat(ChatRepository<PersonalChat> personalChatRepository, ChatRepository<GroupChat> groupChatChatRepository) {
        return new RetrieveAnyChatUseCase(retrievePersonalChat(personalChatRepository), retrieveGroupChat(groupChatChatRepository));
    }

    @Bean
    @Qualifier("retrievePersonalChat")
    public RetrieveChat retrievePersonalChat(ChatRepository<PersonalChat> personalChatRepository) {
        return new PersonalChatUseCase(personalChatRepository);
    }

    @Bean
    @Qualifier("retrieveGroupChat")
    public RetrieveChat retrieveGroupChat(ChatRepository<GroupChat> groupChatChatRepository) {
        return new GroupChatUseCase(groupChatChatRepository);
    }

    @Bean
    public SendMessage sendMessage(ServicesFactory servicesFactory) {
        return new SendMessageUseCase(servicesFactory);
    }

    @Bean
    public RedactMessage redactMessage(ServicesFactory servicesFactory) {
        return new RedactMessageUseCase(servicesFactory);
    }

    @Bean
    public DeleteMessage deleteMessage(ServicesFactory servicesFactory){
        return new DeleteMessageUseCase(servicesFactory);
    }
}
