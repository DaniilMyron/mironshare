package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.SendMessage;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.fatory.ServicesFactory;
import com.miron.directservice.domain.valueObject.Message;

public class SendMessageUseCase implements SendMessage {
    private final ServicesFactory servicesFactory;

    public SendMessageUseCase(ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
    }

    @Override
    public Message sendMessageInChat(Chat chat, Message message) {
        if(chat instanceof PersonalChat) {
            servicesFactory.getPersonalChatService().addMessage(message, chat.getId());
        } else if(chat instanceof GroupChat) {
            servicesFactory.getGroupChatService().addMessage(message, chat.getId());
        }
        return message;
    }
}
