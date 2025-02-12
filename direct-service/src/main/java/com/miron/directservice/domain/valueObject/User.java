package com.miron.directservice.domain.valueObject;

public class User implements ValueObject<Integer>{
    private int id;
    private String username;
    private String profilePicture;
    private String personalInformation;

    public User(String username, String profilePicture, String personalInformation) {
        this.username = username;
        this.profilePicture = profilePicture;
        this.personalInformation = personalInformation;
    }

    @Override
    public Integer getValue() {
        return this.id;
    }
}
