package com.miron.directservice.domain.spi;

import com.miron.directservice.domain.entity.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatRepository<T extends Chat> {
    T save(T chat);
    T findById(UUID id);
    List<T> findAll();
    void deleteById(UUID id);
}
