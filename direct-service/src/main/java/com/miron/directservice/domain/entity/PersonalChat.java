package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.User;


public class PersonalChat extends Chat{
    private User sender;
    private User receiver;

    public PersonalChat(ChatName name, User sender, User receiver) {
        super(name);
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public PersonalChat addMessage(Message message) {
        if(message.getSenderId() != sender.getValue() && message.getSenderId() != receiver.getValue()){
            throw new RuntimeException("You are not the part of chat");
        }
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
