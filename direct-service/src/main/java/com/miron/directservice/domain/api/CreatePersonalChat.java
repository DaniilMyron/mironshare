package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.valueObject.User;

public interface CreatePersonalChat {
    PersonalChat createChat(String template, String username);
}
