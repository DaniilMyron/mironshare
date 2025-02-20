package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.valueObject.Message;

public interface RedactMessage {
    Message redactMessageInChat(Chat chat, Message message);
}
