package com.miron.directservice.domain.service;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.valueObject.Message;

import java.util.List;
import java.util.UUID;

@DomainService
public class GroupChatBasicService implements ChatBasicService<GroupChat> {
    private final ChatRepository<GroupChat> chatRepository;
    private final MessageBasicService messageService;

    public GroupChatBasicService(ChatRepository<GroupChat> chatRepository, MessageBasicService messageService) {
        this.chatRepository = chatRepository;
        this.messageService = messageService;
    }

    @Override
    public GroupChat createChat(GroupChat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Message addMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        var createdMessage = messageService.createMessage(message);
        chatRepository.save(chat.addMessage(createdMessage));
        return createdMessage;
    }

    @Override
    public GroupChat clearChat(UUID chatId) {
        var chat = chatRepository.findById(chatId);
        messageService.deleteMessages(chat);
        return chat.clearChat();
    }

    @Override
    public Message deleteMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        if(!chat.getMessages().contains(message)) {
            throw new RuntimeException("Message is not in chat");
        }
        messageService.deleteMessage(message);
        chat.deleteMessage(message);
        return message;
    }

    @Override
    public Message redactMessage(Message message, UUID chatId) {
        var chat = chatRepository.findById(chatId);
        var redactedMessage = messageService.redactMessage(message);
        chatRepository.save(chat.redactMessage(redactedMessage));
        return redactedMessage;
    }

    @Override
    public List<GroupChat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public GroupChat getChatById(UUID chatId) {
        return chatRepository.findById(chatId);
    }
}
