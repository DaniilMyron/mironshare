package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.Message;

import java.util.List;

public class GroupChat extends Chat {

    public GroupChat(ChatName name, List<Message> messages) {
        super(name, messages);
    }

    @Override
    public Chat addMessage(Message message) {
        return null;
    }

    @Override
    public PersonalChat clearChat() {
        return null;
    }

    @Override
    public PersonalChat deleteMessage(Message message) {
        return null;
    }

    @Override
    public PersonalChat redactMessage(Message message) {
        return null;
    }
}
