package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.RetrieveChats;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.fatory.ServicesFactory;

import java.util.List;

public class RetrieveAllChatsUseCase implements RetrieveChats {
    private final ServicesFactory servicesFactory;

    public RetrieveAllChatsUseCase(ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
    }

    @Override
    public List<Chat> retrieveAllChats() {
        return servicesFactory.getAllChatServices()
                .stream()
                .flatMap(listMap -> listMap.getAllChats().stream())
                .toList();
    }
}
