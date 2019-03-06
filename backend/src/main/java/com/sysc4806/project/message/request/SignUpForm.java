package com.sysc4806.project.message.request;

import javax.validation.constraints.*;

public class SignUpForm {
    @NotBlank
    @Size(min=3, max = 40)
    private String name;

    @NotBlank
    @Size(min=3, max = 40)
    private String username;

    private String role;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
