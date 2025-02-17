package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainQualifier;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;

import java.util.UUID;

@DomainQualifier("retrievePersonalChat")
@DomainUseCase
public class PersonalChatUseCase implements RetrieveChat{
    private final ChatRepository<PersonalChat> chatRepository;

    public PersonalChatUseCase(ChatRepository<PersonalChat> chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public PersonalChat retrieveChat(UUID chatId) {
        return chatRepository.findById(chatId);
    }
}
