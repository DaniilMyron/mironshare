package com.miron.directservice.infrastructure.controller;

import com.miron.directservice.domain.api.*;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.infrastructure.controller.model.MessageIdRequest;
import com.miron.directservice.infrastructure.controller.model.MessageTextRequest;
import com.miron.directservice.infrastructure.controller.model.MessagesResponse;
import com.miron.directservice.infrastructure.controller.model.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/direct")
public class BasicChatController {
    private static final int SENDER_ID = 1;
    private final RetrieveChats retrieveChatsCommand;
    private final RetrieveChat retrieveChatCommand;
    private final SendMessage sendMessageCommand;
    private final RedactMessage redactMessageCommand;
    private final DeleteMessage deleteMessageCommand;

    public BasicChatController(RetrieveChats retrieveChatsCommand,
                               @Qualifier("retrieveAnyChat") RetrieveChat retrieveChatCommand,
                               SendMessage sendMessageCommand,
                               RedactMessage redactMessageCommand,
                               DeleteMessage deleteMessageCommand) {
        this.retrieveChatsCommand = retrieveChatsCommand;
        this.retrieveChatCommand = retrieveChatCommand;
        this.sendMessageCommand = sendMessageCommand;
        this.redactMessageCommand = redactMessageCommand;
        this.deleteMessageCommand = deleteMessageCommand;
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsList() {
        var response = retrieveChatsCommand.retrieveAllChats()
                .stream()
                .map(ChatResponse::new)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatMessages(@PathVariable("id") UUID id) {
        retrieveChat(id).clearChat();
        return ResponseEntity.noContent().build();
    }

    //OR MAKE IT IN SERVICE: response = service1.retrieve() != null ? service1.retrieve() : service2.retrieve()

    @GetMapping("/{id}")
    public ResponseEntity<List<MessagesResponse>> retrieveChatMessages(@PathVariable("id") UUID id) {
        var chat = retrieveChat(id);
        var response = chat.getMessages()
                .stream()
                .map(MessagesResponse::new)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}/find-message")
    public ResponseEntity<List<MessagesResponse>> findMessage(@PathVariable("id") UUID id,
                                                              @RequestBody MessageTextRequest messageTextRequest) {
        var chat = retrieveChat(id);
        var response = chat.getMessages()
                .stream()
                .filter(messageValue -> messageValue.getValue()
                        .toLowerCase()
                        .contains(messageTextRequest.text()
                                .toLowerCase()))
                .map(MessagesResponse::new)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}/redact")
    public ResponseEntity<MessagesResponse> redactMessage(@PathVariable("id") UUID id,
                                                          @RequestBody MessageIdRequest messageIdRequest) {
        var chat = retrieveChat(id);
        var message = chat.getMessages()
                .stream()
                .filter(messageValue -> messageValue.getId()
                        .equals(messageIdRequest.id()))
                .findAny()
                .orElseThrow(RuntimeException::new);
        var response = redactMessageCommand.redactMessageInChat(chat, message.setText(messageIdRequest.redactedText()));
        return ResponseEntity.ok().body(new MessagesResponse(response));
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<MessagesResponse> sendMessage(@PathVariable("id") UUID id,
                                                        @RequestBody MessageTextRequest messageTextRequest) {
        var chat = retrieveChat(id);
        var response = sendMessageCommand.sendMessageInChat(chat, new Message(messageTextRequest.text(), SENDER_ID));
        return ResponseEntity.ok().body(new MessagesResponse(response));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") UUID id,
                                              @RequestBody MessageIdRequest messageIdRequest) {
        var chat = retrieveChat(id);
        var message = chat.getMessages()
                .stream()
                .filter(messageValue -> messageValue.getId()
                        .equals(messageIdRequest.id()))
                .findAny()
                .orElseThrow(RuntimeException::new);
        deleteMessageCommand.deleteMessageByChatId(chat, message);
        return ResponseEntity.noContent().build();
    }

    private Chat retrieveChat(UUID chatId){
        return retrieveChatCommand.retrieveChat(chatId);
    }
}
