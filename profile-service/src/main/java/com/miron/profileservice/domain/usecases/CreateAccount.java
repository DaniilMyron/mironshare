package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

public interface CreateAccount {
    Account execute(String name, String password);
}
