package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.spi.ChatRepository;

import java.util.UUID;

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
