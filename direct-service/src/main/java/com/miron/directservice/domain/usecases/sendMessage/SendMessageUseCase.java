package com.miron.directservice.domain.usecases.sendMessage;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.valueObject.Message;

public class SendMessageUseCase implements SendMessage{
    private final ChatBasicService<PersonalChat> personalChatBasicService;
    private final ChatBasicService<GroupChat> groupChatBasicService;

    public SendMessageUseCase(ChatBasicService<PersonalChat> personalChatBasicService, ChatBasicService<GroupChat> groupChatBasicService) {
        this.personalChatBasicService = personalChatBasicService;
        this.groupChatBasicService = groupChatBasicService;
    }

    @Override
    public Message sendMessageInChat(Chat chat, Message message) {
        if(chat instanceof PersonalChat) {
            personalChatBasicService.addMessage(message, chat.getId());
        } else if(chat instanceof GroupChat) {
            groupChatBasicService.addMessage(message, chat.getId());
        }
        return message;
    }
}
