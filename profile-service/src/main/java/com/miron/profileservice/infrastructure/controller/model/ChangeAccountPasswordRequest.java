package com.miron.profileservice.infrastructure.controller.model;

public record ChangeAccountPasswordRequest(String username, String oldPassword, String newPassword) {
}
