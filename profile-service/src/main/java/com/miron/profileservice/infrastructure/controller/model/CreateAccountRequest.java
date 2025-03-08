package com.miron.profileservice.infrastructure.controller.model;

public record CreateAccountRequest(String username, String password, String accountName) {
}
