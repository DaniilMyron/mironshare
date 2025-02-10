package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatId;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.Message;

import java.util.List;
import java.util.UUID;

public class Chat {
    private ChatId id;
    private ChatName name;
    private List<Message> messages;

    public Chat(ChatName name, List<Message> messages) {
        this.id = new ChatId();
        this.name = name;
        this.messages = messages;
    }

    public Chat addMessage(Message message) {
        messages.add(message);
        return this;
    }

    public Chat clearChat(){
        messages.clear();
        return this;
    }

    public Chat deleteMessage(Message message) {
        messages.remove(message);
        return this;
    }

    public Chat redactMessage(Message message) {
        messages.set(messages.indexOf(message), message);
        return this;
    }

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
