package com.miron.profileservice.infrastructure.controller;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.infrastructure.controller.model.AccountResponse;
import com.miron.profileservice.infrastructure.controller.model.AccountsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        var account = accountService.retrieveUser(id);
        var response = new AccountResponse(account);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/retrieve-profiles")
    public ResponseEntity<List<AccountResponse>> retrieveProfiles(@RequestBody AccountsRequest accountsRequest) {
        var accounts = accountService.retrieveUsers(accountsRequest.getUsersId());
        var response = accounts.stream()
                .map(AccountResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(response);
    }
}
