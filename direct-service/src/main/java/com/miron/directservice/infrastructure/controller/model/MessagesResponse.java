package com.miron.directservice.infrastructure.controller.model;

import com.miron.directservice.domain.entity.Message;

public record MessagesResponse(String text, int senderId) {
    public MessagesResponse(Message message) {
        this(message.getValue(), message.getSenderId());
    }
}
