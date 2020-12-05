package com.stp.crud.model;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.USER_PERMISSIONS)),
    ADMIN(Set.of(Permission.ADMIN_PERMISSIONS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) { this.permissions = permissions; }

    public Set<Permission> getPermissions() { return permissions; }

    public Set<SimpleGrantedAuthority> getUserAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
