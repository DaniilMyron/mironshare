package com.miron.profileservice.domain.entity;

import com.miron.profileservice.domain.valueObjects.AccountId;
import com.miron.profileservice.domain.valueObjects.AccountName;
import com.miron.profileservice.domain.valueObjects.AccountPassword;

import java.util.UUID;

public class Account {
    private AccountId id;
    private AccountName name;
    private AccountPassword password;
    private AdditionalInformation additionalInformation;

    public Account(String name, String password) {
        this.id = new AccountId();
        this.name = new AccountName(name);
        this.password = new AccountPassword(password);
    }

    public UUID getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public AdditionalInformation getAdditionalInformation() {
        if(additionalInformation == null) {
            return null;
        }
        return additionalInformation;
    }

    public Account changeAccountName(AccountName name) {
        this.name = name;
        return this;
    }

    public Account changeAccountPassword(AccountPassword oldPassword, AccountPassword newPassword) {
        if(!oldPassword.getValue().equals(newPassword.getValue())) {
            throw new IllegalArgumentException("Old and new passwords do not match");
        }
        this.password = newPassword;
        return this;
    }

    public Account setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}
