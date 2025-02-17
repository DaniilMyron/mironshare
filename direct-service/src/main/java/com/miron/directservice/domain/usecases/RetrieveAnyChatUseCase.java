package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.springAnnotations.DomainQualifier;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;

import java.util.UUID;

@DomainUseCase
@DomainQualifier("retrieveAnyChat")
public class RetrieveAnyChatUseCase implements RetrieveChat{
    @DomainQualifier("retrievePersonalChat")
    private final RetrieveChat retrievePersonalChat;
    @DomainQualifier("retrieveGroupChat")
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
