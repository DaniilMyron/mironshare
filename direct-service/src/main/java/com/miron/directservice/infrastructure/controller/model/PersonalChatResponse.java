package com.miron.directservice.infrastructure.controller.model;

import com.miron.directservice.domain.entity.Chat;

import java.util.UUID;

public record PersonalChatResponse(UUID id, String name) {
    public PersonalChatResponse(Chat chat){
        this(chat.getId(), chat.getName());
    }
}
