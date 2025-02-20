package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.RetrieveChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;

import java.util.UUID;

public class PersonalChatUseCase implements RetrieveChat {
    private final ChatRepository<PersonalChat> chatRepository;

    public PersonalChatUseCase(ChatRepository<PersonalChat> chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public PersonalChat retrieveChat(UUID chatId) {
        return chatRepository.findById(chatId);
    }
}
