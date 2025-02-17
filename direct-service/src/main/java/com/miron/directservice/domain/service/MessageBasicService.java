package com.miron.directservice.domain.service;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.valueObject.Message;

public interface MessageBasicService {
    Message createMessage(Message message);
    void deleteMessages(Chat chat);
    void deleteMessage(Message message);
    Message redactMessage(Message message);
}
