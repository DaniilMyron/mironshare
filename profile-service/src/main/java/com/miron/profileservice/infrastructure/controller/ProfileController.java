package com.miron.profileservice.infrastructure.controller;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.infrastructure.controller.model.AccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final AccountService accountService;

    public ProfileController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> retrieveProfile(@PathVariable UUID id) {
        var account = new Account("miron2", "12345678910", "danya");//accountService.retrieveUser(id);
        var response = new AccountResponse(account);
        return ResponseEntity.ok().body(response);
    }
}
