package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.Message;

import java.util.UUID;

public interface ChatBasicService<T extends Chat> {
    T createChat(T chat);
    T addMessage(Message message, UUID chatId);
    T clearChat(UUID chatId);
    T deleteMessage(Message message, UUID chatId);
    T redactMessage(Message message, UUID chatId);
}
