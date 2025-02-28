package com.miron.profileservice.domain.entity;

import com.miron.profileservice.domain.valueObjects.AccountId;
import com.miron.profileservice.domain.valueObjects.AccountName;
import com.miron.profileservice.domain.valueObjects.AccountPassword;

import java.util.List;
import java.util.UUID;

public class Account {
    private AccountId id;
    private AccountName name;
    private AccountPassword password;
    private List<Account> friends;
    private List<Account> subscribers;
    private AdditionalInformation additionalInformation;

    public Account(String name, String password) {
        this.id = new AccountId();
        this.name = new AccountName(name);
        this.password = new AccountPassword(password);
    }

    public Account subscribeOnUser(Account user) {
        if(this.friends.contains(user)) {
            throw new IllegalArgumentException("Friend already exists");
        }
        if(!this.subscribers.contains(user)) {
            user.subscribers.add(this);
            return this;
        }
        this.friends.add(user);
        user.friends.add(this);
        this.subscribers.remove(user);
        return this;
    }

    public Account unsubscribeFromUser(Account user) {
        if(this.friends.contains(user)) {
            this.friends.remove(user);
            this.subscribers.add(user);
            user.friends.remove(this);
            return this;
        }
        else if(user.subscribers.contains(this)) {
            user.subscribers.remove(this);
            return this;
        }
        throw new IllegalArgumentException("Friend does not exist");
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

    public Account setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}
