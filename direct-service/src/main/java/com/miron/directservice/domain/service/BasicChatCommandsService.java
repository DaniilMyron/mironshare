package com.miron.directservice.domain.service;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.valueObject.Message;

import java.util.UUID;

@DomainService
public class BasicChatCommandsService<T extends Chat> implements ChatBasicService<T> {
    private final ChatRepository<T> chatRepository;
    private final MessageBasicService messageService;

    public BasicChatCommandsService(ChatRepository<T> chatRepository, MessageBasicService messageService) {
        this.chatRepository = chatRepository;
        this.messageService = messageService;
    }

    @Override
    public T createChat(T chat) {
        return chatRepository.save(chat);
    }

    @Override
    public T addMessage(Message message, UUID chatId) {
        T chat = chatRepository.findById(chatId);
        var createdMessage = messageService.createMessage(message);
        return chatRepository.save((T) chat.addMessage(createdMessage));
    }

    @Override
    public T clearChat(UUID chatId) {
        var chat = chatRepository.findById(chatId);
        messageService.deleteMessages(chatId);
        return (T) chat.clearChat();
    }

    @Override
    public T deleteMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        if(!chat.getMessages().contains(message)) {
            return null;
        }
        messageService.deleteMessage(message);
        return (T) chat.deleteMessage(message);
    }

    @Override
    public T redactMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        var redactedMessage = messageService.redactMessage(message);
        return chatRepository.save((T) chat.redactMessage(redactedMessage));
    }
}
