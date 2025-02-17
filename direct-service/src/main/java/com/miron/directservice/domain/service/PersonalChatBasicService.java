package com.miron.directservice.domain.service;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.valueObject.Message;

import java.util.List;
import java.util.UUID;

@DomainService
public class PersonalChatBasicService implements ChatBasicService<PersonalChat> {
    private final ChatRepository<PersonalChat> chatRepository;
    private final MessageBasicService messageService;

    public PersonalChatBasicService(ChatRepository<PersonalChat> chatRepository, MessageBasicService messageService) {
        this.chatRepository = chatRepository;
        this.messageService = messageService;
    }

    @Override
    public PersonalChat createChat(PersonalChat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public PersonalChat addMessage(Message message, UUID chatId) {
        PersonalChat chat = chatRepository.findById(chatId);
        var createdMessage = messageService.createMessage(message);
        return chatRepository.save(chat.addMessage(createdMessage));
    }

    @Override
    public PersonalChat clearChat(UUID chatId) {
        var chat = chatRepository.findById(chatId);
        messageService.deleteMessages(chat);
        return chat.clearChat();
    }

    @Override
    public PersonalChat deleteMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        if(!chat.getMessages().contains(message)) {
            return null;
        }
        messageService.deleteMessage(message);
        return chat.deleteMessage(message);
    }

    @Override
    public PersonalChat redactMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        var redactedMessage = messageService.redactMessage(message);
        return chatRepository.save(chat.redactMessage(redactedMessage));
    }

    @Override
    public List<PersonalChat> getAllChats() {
        return chatRepository.findAll();
    }
}
