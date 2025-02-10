package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.Message;

import java.util.UUID;

public interface ChatBasicService {
    Chat createChat(Chat chat);
    Chat addMessage(Message message, UUID chatId);
    Chat clearChat(UUID chatId);
    Chat deleteMessage(Message message, UUID chatId);
    Chat redactMessage(Message message, UUID chatId);
}
