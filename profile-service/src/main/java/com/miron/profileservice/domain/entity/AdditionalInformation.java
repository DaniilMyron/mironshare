package com.miron.profileservice.domain.entity;

import com.miron.profileservice.domain.valueObjects.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class AdditionalInformation {
    private final AdditionalInformationId additionalInformationId;
    private AccountPicture accountPicture;
    private AgeInformation ageInformation;
    private GenderInformation genderInformation;
    private AboutInformation aboutInformation;

    private AdditionalInformation(){
        additionalInformationId = new AdditionalInformationId();
    }

    public UUID getAdditionalInformationId() {
        return additionalInformationId.getValue();
    }

    public String getAccountPicture() {
        return accountPicture.getValue();
    }

    public GenderInformation getGenderInformation() {
        return genderInformation.getValue();
    }

    public int getAgeInformation() {
        return ageInformation.getValue();
    }

    public String getAboutInformation() {
        return aboutInformation.getValue();
    }

    public AdditionalInformation changeAccountPicture(String picture) {
        this.accountPicture = new AccountPicture(picture.getBytes(StandardCharsets.UTF_8));
        return this;
    }

    public AdditionalInformation changeAgeInformation(int age) {
        this.ageInformation = new AgeInformation(age);
        return this;
    }

    public AdditionalInformation changeGenderInformation(String gender) {
        this.genderInformation = GenderInformation.valueOf(gender);
        return this;
    }

    public AdditionalInformation changeAboutInformation(String about) {
        this.aboutInformation = new AboutInformation(about);
        return this;
    }

    public static Builder Builder() {
        return new AdditionalInformation().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setAccountPicture(String accountPicture) {
            AdditionalInformation.this.accountPicture = new AccountPicture(accountPicture.getBytes(StandardCharsets.UTF_8));
            return this;
        }

        public Builder setAge(int age) {
            AdditionalInformation.this.ageInformation = new AgeInformation(age);
            return this;
        }

        public Builder setAbout(String about) {
            AdditionalInformation.this.aboutInformation = new AboutInformation(about);
            return this;
        }

        public Builder setGender(String gender) {
            AdditionalInformation.this.genderInformation = GenderInformation.valueOf(gender);
            return this;
        }

        public AdditionalInformation build() {
            return AdditionalInformation.this;
        }

    }
}
