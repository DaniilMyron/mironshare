package com.miron.directservice.domain.spi;

import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {
    Message save(Message message);
    Message findById(UUID id);
    Message findBySenderId(int senderId);
    List<Message> findAll();
    void deleteById(UUID id);
    void deleteAllByChatId(UUID chatId);
}
