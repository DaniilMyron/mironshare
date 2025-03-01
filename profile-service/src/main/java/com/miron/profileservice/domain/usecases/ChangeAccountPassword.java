package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

public interface ChangeAccountPassword {
    Account execute(String username, String oldPassword, String newPassword);
}
