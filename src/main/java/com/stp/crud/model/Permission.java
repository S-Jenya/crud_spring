package com.stp.crud.model;

public enum Permission {
    ADMIN_PERMISSIONS("admin_permissions"),
    USER_PERMISSIONS("user_permissions");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
