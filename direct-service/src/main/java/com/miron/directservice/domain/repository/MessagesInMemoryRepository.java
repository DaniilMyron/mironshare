package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.entity.Message;

import java.util.*;

public class MessagesInMemoryRepository implements MessageRepository {
    private final Map<UUID, Message> messages = new HashMap<>();

    @Override
    public Message save(Message message) {
        messages.computeIfPresent(message.getId(), (key, value) -> value.setText(message.getValue()));
        messages.putIfAbsent(message.getId(), message);
        return messages.get(message.getId());
    }

    @Override
    public Message findById(UUID id) {
        return messages.get(id);
    }

    @Override
    public Message findBySenderId(int senderId) {
        return messages.values()
                .stream()
                .filter(m -> m.getSenderId() == senderId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messages.values());
    }

    @Override
    public void deleteById(UUID id) {
        messages.remove(id);
    }

    @Override
    public void deleteAllChatMessages(Chat chat) {
        for (Message message : chat.getMessages()) {
            messages.remove(message.getId());
        }
    }
}
