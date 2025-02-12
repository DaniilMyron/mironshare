package com.miron.directservice.domain.valueObject;

public class User implements ValueObject<Integer>{
    private final int id;
    private String username;
    private String profilePicture;
    private String personalInformation;

    public User(int id, String username, String profilePicture, String personalInformation) {
        this.id = id;
        this.username = username;
        this.profilePicture = profilePicture;
        this.personalInformation = personalInformation;
    }

    @Override
    public Integer getValue() {
        return this.id;
    }
}
