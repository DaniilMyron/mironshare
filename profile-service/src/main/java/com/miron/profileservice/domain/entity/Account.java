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

    public Account changeAccountName(String name) {
        this.name = new AccountName(name);
        return this;
    }

    public Account changeAccountPassword(String oldPassword, String newPassword) {
        var oldAccountPassword = new AccountPassword(oldPassword);
        var newAccountPassword = new AccountPassword(newPassword);
        if(!oldAccountPassword.getValue().equals(this.password.getValue())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        this.password = newAccountPassword;
        return this;
    }

    public UUID getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getPassword() {
        return password.getValue();
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
