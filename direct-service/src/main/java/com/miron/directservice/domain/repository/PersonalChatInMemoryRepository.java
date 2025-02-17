package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;

import java.util.*;

public class PersonalChatInMemoryRepository implements ChatRepository<PersonalChat> {
    private final Map<UUID, PersonalChat> chats = new HashMap<>();

    @Override
    public PersonalChat save(PersonalChat chat) {
        chats.put(chat.getId(), chat);
        return chats.get(chat.getId());
    }

    @Override
    public PersonalChat findById(UUID id) {
        return chats.get(id);
    }

    @Override
    public List<PersonalChat> findAll() {
        return new ArrayList<>(chats.values());
    }

    @Override
    public void deleteById(UUID id) {
        chats.remove(id);
    }
}
