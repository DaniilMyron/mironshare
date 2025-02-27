package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.Message;

public interface SendMessage {
    Message sendMessageInChat(Chat chat, Message message);
}
