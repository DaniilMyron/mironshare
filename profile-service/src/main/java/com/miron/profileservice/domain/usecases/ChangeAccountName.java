package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

import java.util.UUID;

public interface ChangeAccountName {
    Account execute(UUID id, String accountName);
}
