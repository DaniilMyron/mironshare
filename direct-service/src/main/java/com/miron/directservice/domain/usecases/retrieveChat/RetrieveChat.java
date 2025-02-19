package com.miron.directservice.domain.usecases.retrieveChat;

import com.miron.directservice.domain.entity.Chat;

import java.util.UUID;

public interface RetrieveChat {
    Chat retrieveChat(UUID chatId);
}
