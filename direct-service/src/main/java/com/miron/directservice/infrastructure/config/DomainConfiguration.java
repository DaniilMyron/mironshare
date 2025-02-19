package com.miron.directservice.infrastructure.config;

import com.miron.directservice.domain.BasePackageClassesSkanMarker;
import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.api.RetrieveChats;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.fatory.ServicesFactory;
import com.miron.directservice.domain.fatory.ServicesFactoryImpl;
import com.miron.directservice.domain.repository.GroupChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.repository.PersonalChatInMemoryRepository;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.api.SendMessage;
import com.miron.directservice.domain.usecases.retrieveChats.RetrieveAllChatsUseCase;
import com.miron.directservice.domain.usecases.sendMessage.SendMessageUseCase;
import com.miron.directservice.domain.usecases.retrieveChat.GroupChatUseCase;
import com.miron.directservice.domain.usecases.retrieveChat.PersonalChatUseCase;
import com.miron.directservice.domain.usecases.retrieveChat.RetrieveAnyChatUseCase;
import com.miron.directservice.domain.api.RetrieveChat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        basePackageClasses = {BasePackageClassesSkanMarker.class, DomainService.class, MessagesInMemoryRepository.class, GroupChatInMemoryRepository.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, DomainRepository.class, DomainUseCase.class})},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MessagesInMemoryRepository.class, GroupChatInMemoryRepository.class})})
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
}
