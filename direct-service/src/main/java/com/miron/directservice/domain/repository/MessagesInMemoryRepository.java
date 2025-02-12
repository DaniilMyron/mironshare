package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.Message;

import java.util.*;

public class MessagesInMemoryRepository implements MessageRepository {
    private final ChatRepository chatRepository;

    private final Map<UUID, Message> messages = new HashMap<>();

    public MessagesInMemoryRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Message save(Message message) {
        messages.put(message.getId(), message);
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
    public void deleteAllByChatId(UUID chatId) {
        var chat = chatRepository.findById(chatId);
        for (Message message : chat.getMessages()) {
            messages.remove(message.getId());
        }
    }
}
