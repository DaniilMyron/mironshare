package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.RetrieveChat;
import com.miron.directservice.domain.entity.Chat;

import java.util.UUID;

public class RetrieveAnyChatUseCase implements RetrieveChat {
    private final RetrieveChat retrievePersonalChat;
    private final RetrieveChat retrieveGroupChat;

    public RetrieveAnyChatUseCase(RetrieveChat retrievePersonalChat, RetrieveChat retrieveGroupChat) {
        this.retrievePersonalChat = retrievePersonalChat;
        this.retrieveGroupChat = retrieveGroupChat;
    }

    @Override
    public Chat retrieveChat(UUID chatId) {
        return retrieveGroupChat.retrieveChat(chatId) != null ? retrieveGroupChat.retrieveChat(chatId) : retrievePersonalChat.retrieveChat(chatId);
    }
}
