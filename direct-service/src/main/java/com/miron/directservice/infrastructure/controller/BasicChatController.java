package com.miron.directservice.infrastructure.controller;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.usecases.RetrieveChat;
import com.miron.directservice.infrastructure.controller.model.MessagesResponse;
import com.miron.directservice.infrastructure.controller.model.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/direct")
public class BasicChatController {
    private final ChatBasicService<GroupChat> groupBasicChatCommandsService;
    private final ChatBasicService<PersonalChat> personalBasicChatCommandsService;
    private final RetrieveChat retrieveChatCommand;

    public BasicChatController(ChatBasicService<GroupChat> groupBasicChatCommandsService, ChatBasicService<PersonalChat> personalBasicChatCommandsService,@Qualifier("retrieveAnyChat") RetrieveChat retrieveChatCommand) {
        this.personalBasicChatCommandsService = personalBasicChatCommandsService;
        this.groupBasicChatCommandsService = groupBasicChatCommandsService;
        this.retrieveChatCommand = retrieveChatCommand;
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getBasicChat() {
        var groupChatResponse = new ArrayList<>(groupBasicChatCommandsService.getAllChats()
                .stream()
                .map(ChatResponse::new)
                .toList());
        var personalChatResponse = new ArrayList<>(personalBasicChatCommandsService.getAllChats()
                .stream()
                .map(ChatResponse::new)
                .toList());
        var responseList = new ArrayList<ChatResponse>();
        responseList.addAll(personalChatResponse);
        responseList.addAll(groupChatResponse);
        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MessagesResponse>> getChatMessages(@PathVariable("id") UUID id) {
        var response = retrieveChatCommand.retrieveChat(id)
                .getMessages()
                .stream()
                .map(MessagesResponse::new)
                .toList();
        return ResponseEntity.ok().body(response);
    }
}
