package com.stp.crud.service;

import javax.validation.constraints.NotBlank;

public class UserRepr {

    private Long id_user;

    @NotBlank /* Spring проверит, чтобы поле не было пустым */
    private String name;

    @NotBlank
    private String role;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;

    public Long getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
