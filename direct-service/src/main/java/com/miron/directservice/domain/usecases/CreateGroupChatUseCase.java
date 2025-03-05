package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.CreateGroupChat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.User;

public class CreateGroupChatUseCase implements CreateGroupChat {
    private final ChatRepository<GroupChat> groupChatRepository;

    public CreateGroupChatUseCase(ChatRepository<GroupChat> groupChatRepository) {
        this.groupChatRepository = groupChatRepository;
    }

    @Override
    public GroupChat createChat(String chatName, User... users) {
        return groupChatRepository.save(new GroupChat(new ChatName(chatName), users));
    }
}
