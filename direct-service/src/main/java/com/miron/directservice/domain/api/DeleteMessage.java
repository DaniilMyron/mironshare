package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.Message;

public interface DeleteMessage {
    void deleteMessageByChatId(Chat chat, Message message);
}
