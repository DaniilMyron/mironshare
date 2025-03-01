package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

import java.util.UUID;

public interface SubscribeOnUser {
    Account execute(String username, Account userToSubscribeOn);
}
