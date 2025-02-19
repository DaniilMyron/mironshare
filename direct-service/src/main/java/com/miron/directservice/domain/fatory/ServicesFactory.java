package com.miron.directservice.domain.fatory;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;

import java.util.List;

public interface ServicesFactory {
    ChatBasicService<PersonalChat> getPersonalChatService();
    ChatBasicService<GroupChat> getGroupChatService();
    List<ChatBasicService> getAllChatServices();
}
