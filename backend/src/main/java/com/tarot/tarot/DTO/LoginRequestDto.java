package com.tarot.tarot.DTO;

public class LoginRequestDto {
    private String nameOrEmail;
    private String password;

    // getters and setters
    public String getNameOrEmail() {
        return nameOrEmail;
    }

    public void setNameOrEmail(String nameOrEmail) {
        this.nameOrEmail = nameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
