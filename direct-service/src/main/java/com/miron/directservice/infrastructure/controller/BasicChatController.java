package com.miron.directservice.infrastructure.controller;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.usecases.sendMessage.SendMessage;
import com.miron.directservice.domain.usecases.retrieveChat.RetrieveChat;
import com.miron.directservice.domain.valueObject.Message;
import com.miron.directservice.infrastructure.controller.model.MessageRequest;
import com.miron.directservice.infrastructure.controller.model.MessagesResponse;
import com.miron.directservice.infrastructure.controller.model.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/direct")
public class BasicChatController {
    private final int SENDER_ID = 1;
    private final ChatBasicService<GroupChat> groupBasicChatCommandsService;
    private final ChatBasicService<PersonalChat> personalBasicChatCommandsService;
    private final RetrieveChat retrieveChatCommand;
    private final SendMessage sendMessageCommand;

    public BasicChatController(ChatBasicService<GroupChat> groupBasicChatCommandsService, ChatBasicService<PersonalChat> personalBasicChatCommandsService, @Qualifier("retrieveAnyChat") RetrieveChat retrieveChatCommand, SendMessage sendMessageCommand) {
        this.personalBasicChatCommandsService = personalBasicChatCommandsService;
        this.groupBasicChatCommandsService = groupBasicChatCommandsService;
        this.retrieveChatCommand = retrieveChatCommand;
        this.sendMessageCommand = sendMessageCommand;
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsList() {
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

    //OR MAKE IT IN SERVICE: response = service1.retrieve() != null ? service1.retrieve() : service2.retrieve()

    @GetMapping("/{id}")
    public ResponseEntity<List<MessagesResponse>> retrieveChatMessages(@PathVariable("id") UUID id) {
        var response = retrieveChatCommand.retrieveChat(id)
                .getMessages()
                .stream()
                .map(MessagesResponse::new)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessagesResponse> sendMessage(@PathVariable("id") UUID id,
                                                        @RequestBody MessageRequest messageRequest) {
        var chat = retrieveChatCommand.retrieveChat(id);
        var response = sendMessageCommand.sendMessageInChat(chat, new Message(messageRequest.text(), SENDER_ID));
        return ResponseEntity.ok().body(new MessagesResponse(response));
    }
}
