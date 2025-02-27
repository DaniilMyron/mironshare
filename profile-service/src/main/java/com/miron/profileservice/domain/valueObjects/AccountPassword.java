package com.miron.profileservice.domain.valueObjects;

public class AccountPassword implements ValueObject<String> {
    private String password;

    public AccountPassword(String password) {
        if(password.isBlank() || password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be blank or empty");
        } else if(password.length() < 10){
            throw new IllegalArgumentException("Password must be at least 10 characters");
        } else if(password.length() > 20){
            throw new IllegalArgumentException("Password must be less than 20 characters");
        }
        this.password = password;
    }

    @Override
    public String getValue() {
        return this.password;
    }
}
