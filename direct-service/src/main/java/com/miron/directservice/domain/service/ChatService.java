package com.miron.directservice.domain.service;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.Message;

import java.util.UUID;

@DomainService
public class ChatService implements ChatBasicService {
    private final ChatRepository chatRepository;
    private final MessageService messageService;

    public ChatService(ChatRepository chatRepository, MessageService messageService) {
        this.chatRepository = chatRepository;
        this.messageService = messageService;
    }

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat addMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        var createdMessage = messageService.createMessage(message);
        return chatRepository.save(chat.addMessage(createdMessage));
    }

    @Override
    public Chat clearChat(UUID chatId) {
        var chat = chatRepository.findById(chatId);
        messageService.deleteMessages(chatId);
        return chat.clearChat();
    }

    @Override
    public Chat deleteMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        messageService.deleteMessage(message);
        return chat.deleteMessage(message);
    }

    @Override
    public Chat redactMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        var redactedMessage = messageService.redactMessage(message);
        return chatRepository.save(chat.redactMessage(redactedMessage));
    }
}
