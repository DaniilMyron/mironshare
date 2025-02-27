package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.ChatName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Chat implements ChatBasicLogic{
    private ChatId id;
    private ChatName name;
    protected List<Message> messages;

    public Chat(ChatName name) {
        this.id = new ChatId();
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public abstract Chat addMessage(Message message);

    public abstract Chat clearChat();

    public abstract Chat deleteMessage(Message message);

    public abstract Chat redactMessage(Message message);

    public UUID getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Message> getMessages() {
        return messages;
    }
}
