package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.DeleteMessage;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.fatory.ServicesFactory;
import com.miron.directservice.domain.entity.Message;

public class DeleteMessageUseCase implements DeleteMessage {
    private final ServicesFactory servicesFactory;

    public DeleteMessageUseCase(ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
    }


    @Override
    public void deleteMessageByChatId(Chat chat, Message message) {
        if(chat instanceof PersonalChat) {
            servicesFactory.getPersonalChatService().deleteMessage(message, chat.getId());
        } else if(chat instanceof GroupChat) {
            servicesFactory.getGroupChatService().addMessage(message, chat.getId());
        }
    }
}
