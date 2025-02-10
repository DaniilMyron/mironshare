package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.spi.ChatRepository;

import java.util.*;

public class ChatInMemoryRepository implements ChatRepository {
    private final Map<UUID, Chat> chats = new HashMap<>();

    @Override
    public Chat save(Chat chat) {
        return chats.put(chat.getId(), chat);
    }

    @Override
    public Chat findById(UUID id) {
        return chats.get(id);
    }

    @Override
    public List<Chat> findAll() {
        return new ArrayList<>(chats.values());
    }

    @Override
    public void deleteById(UUID id) {
        chats.remove(id);
    }
}
