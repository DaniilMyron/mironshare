package com.miron.directservice.infrastructure.controller;

import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.service.BasicChatCommandsService;
import com.miron.directservice.infrastructure.controller.model.PersonalChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/direct")
public class BasicChatController {
    private final BasicChatCommandsService<PersonalChat> basicChatCommandsService;

    public BasicChatController(BasicChatCommandsService<PersonalChat> basicChatCommandsService) {
        this.basicChatCommandsService = basicChatCommandsService;
    }

    @GetMapping
    public ResponseEntity<List<PersonalChatResponse>> getBasicChat() {
        var response = basicChatCommandsService.getAllChats().stream().map(PersonalChatResponse::new).toList();
        return ResponseEntity.ok().body(response);
    }
}
