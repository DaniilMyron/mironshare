package com.miron.profileservice.infrastructure.controller.model;

public class AccountsRequest {
    private String[] usersId;
    public AccountsRequest(String... usersId) {
        this.usersId = usersId;
    }

    public String[] getUsersId() {
        return usersId;
    }
}
