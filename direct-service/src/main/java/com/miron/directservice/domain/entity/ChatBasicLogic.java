package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.Message;

public interface ChatBasicLogic {
    Chat addMessage(Message message);
    Chat clearChat();
    Chat deleteMessage(Message message);
    Chat redactMessage(Message message);
}
