package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;

import java.util.List;

public interface RetrieveChats {
    List<Chat> retrieveAllChats();
}
