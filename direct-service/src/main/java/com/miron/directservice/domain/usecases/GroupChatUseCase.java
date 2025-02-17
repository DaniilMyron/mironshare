package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainQualifier;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;

import java.util.UUID;

@DomainQualifier("retrieveGroupChat")
@DomainUseCase
public class GroupChatUseCase implements RetrieveChat{
    private final ChatRepository<GroupChat> chatRepository;

    public GroupChatUseCase(ChatRepository<GroupChat> chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public GroupChat retrieveChat(UUID chatId) {
        return chatRepository.findById(chatId);
    }
}
