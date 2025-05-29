package com.sanchae.coderun.dto.user;


public class UserSignupRequestDto {
    private String username;
    private String password;
    private String email;

    public UserSignupRequestDto() {}

    public UserSignupRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
