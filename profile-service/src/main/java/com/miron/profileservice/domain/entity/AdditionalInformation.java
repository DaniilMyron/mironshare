package com.miron.profileservice.domain.entity;

import com.miron.profileservice.domain.valueObjects.AboutInformation;
import com.miron.profileservice.domain.valueObjects.AccountPicture;
import com.miron.profileservice.domain.valueObjects.AgeInformation;
import com.miron.profileservice.domain.valueObjects.GenderInformation;

import java.nio.charset.StandardCharsets;

public class AdditionalInformation {
    private AccountPicture accountPicture;
    private AgeInformation ageInformation;
    private GenderInformation genderInformation;
    private AboutInformation aboutInformation;

    private AdditionalInformation(){}

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

    public static Builder Builder() {
        return new AdditionalInformation().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setProfilePicture(String profilePicture) {
            AdditionalInformation.this.accountPicture = new AccountPicture(profilePicture.getBytes(StandardCharsets.UTF_8));
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
