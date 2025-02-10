package com.miron.directservice.domain.spi;

import com.miron.directservice.domain.entity.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatRepository {
    Chat save(Chat chat);
    Chat findById(UUID id);
    List<Chat> findAll();
    void deleteById(UUID id);
}
