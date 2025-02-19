package com.miron.directservice.domain.fatory;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;

import java.util.List;

public class ServicesFactoryImpl implements ServicesFactory {
    private final ChatBasicService<PersonalChat> personalChatService;
    private final ChatBasicService<GroupChat> groupChatService;

    public ServicesFactoryImpl(ChatBasicService<PersonalChat> personalChatService, ChatBasicService<GroupChat> groupChatService) {
        this.personalChatService = personalChatService;
        this.groupChatService = groupChatService;
    }

    @Override
    public ChatBasicService<PersonalChat> getPersonalChatService() {
        return personalChatService;
    }

    @Override
    public ChatBasicService<GroupChat> getGroupChatService() {
        return groupChatService;
    }

    @Override
    public List<ChatBasicService> getAllChatServices() {
        return List.of(personalChatService, groupChatService);
    }
}
