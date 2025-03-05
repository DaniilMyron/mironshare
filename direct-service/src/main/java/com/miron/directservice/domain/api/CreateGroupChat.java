package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.valueObject.User;

public interface CreateGroupChat {
    GroupChat createChat(String chatName, User... users);
}
