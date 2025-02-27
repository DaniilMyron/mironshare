package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.Message;

import java.util.List;
import java.util.UUID;

public interface ChatBasicService<T extends Chat> {
    T createChat(T chat);
    Message addMessage(Message message, UUID chatId);
    T clearChat(UUID chatId);
    Message deleteMessage(Message message, UUID chatId);
    Message redactMessage(Message message, UUID chatId);
    List<T> getAllChats();
    T getChatById(UUID chatId);
}
