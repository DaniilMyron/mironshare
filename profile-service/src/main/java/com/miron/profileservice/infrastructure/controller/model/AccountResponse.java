package com.miron.profileservice.infrastructure.controller.model;

import com.miron.profileservice.domain.entity.Account;


public class AccountResponse{
    private String accountName;
    private String accountPicture;
    private Integer userAge;
    private String userGender;
    private String userAbout;
    public AccountResponse(Account account) {
        this.accountName = account.getAccountName();
        if(account.getAdditionalInformation() != null) {
            this.accountPicture = account.getAdditionalInformation().getAccountPicture();
            this.userAge = account.getAdditionalInformation().getAgeInformation();
            this.userGender = account.getAdditionalInformation().getGenderInformation().name();
            this.userAbout = account.getAdditionalInformation().getAboutInformation();
        }
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPicture() {
        return accountPicture;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserAbout() {
        return userAbout;
    }
}
