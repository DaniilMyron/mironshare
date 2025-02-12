package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.Message;

import java.util.List;

public class GroupChat extends Chat {

    public GroupChat(ChatName name, List<Message> messages) {
        super(name, messages);
    }

    @Override
    public GroupChat addMessage(Message message) {
        messages.add(message);
        return this;
    }

    @Override
    public GroupChat clearChat() {
        messages.clear();
        return this;
    }

    @Override
    public GroupChat deleteMessage(Message message) {
        messages.remove(message);
        return this;
    }

    @Override
    public GroupChat redactMessage(Message message) {
        messages.set(messages.indexOf(message), message);
        return this;
    }
}
