package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.Message;
import com.miron.directservice.domain.valueObject.User;

import java.util.List;

public class PersonalChat extends Chat{
    private User sender;
    private User receiver;

    public PersonalChat(ChatName name, List<Message> messages, User sender, User receiver) {
        super(name, messages);
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public PersonalChat addMessage(Message message) {
        messages.add(message);
        return this;
    }

    @Override
    public PersonalChat clearChat() {
        messages.clear();
        return this;
    }

    @Override
    public PersonalChat deleteMessage(Message message) {
        messages.remove(message);
        return this;
    }

    @Override
    public PersonalChat redactMessage(Message message) {
        messages.set(messages.indexOf(message), message);
        return this;
    }
}
