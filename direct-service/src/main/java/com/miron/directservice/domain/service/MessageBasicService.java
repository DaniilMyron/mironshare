package com.miron.directservice.domain.service;

import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.Message;

import java.util.UUID;

public interface MessageBasicService {
    Message createMessage(Message message);
    void deleteMessages(UUID chatId);
    void deleteMessage(Message message);
    Message redactMessage(Message message);
}
