package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.RedactMessage;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.fatory.ServicesFactory;
import com.miron.directservice.domain.valueObject.Message;

public class RedactMessageUseCase implements RedactMessage {
    private final ServicesFactory servicesFactory;

    public RedactMessageUseCase(ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
    }

    @Override
    public Message redactMessageInChat(Chat chat, Message message) {
        if(chat instanceof PersonalChat) {
            return servicesFactory.getPersonalChatService().redactMessage(message, chat.getId());
        } else if(chat instanceof GroupChat) {
            return servicesFactory.getGroupChatService().redactMessage(message, chat.getId());
        }
        throw new IllegalArgumentException("Unsupported chat type");
    }
}
