package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;

import java.util.*;

@DomainRepository
public class BasicChatInMemoryRepository<T extends Chat> implements ChatRepository<T> {
    private final Map<UUID, T> chats = new HashMap<>();

    @Override
    public T save(T chat) {
        chats.put(chat.getId(), chat);
        return chats.get(chat.getId());
    }

    @Override
    public T findById(UUID id) {
        return chats.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(chats.values());
    }

    @Override
    public void deleteById(UUID id) {
        chats.remove(id);
    }
}
