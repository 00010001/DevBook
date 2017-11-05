package com.devbook.model;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN", "This is Admin Role Description"),
    ROLE_USER("ROLE_USER","This is User Role Description");

    private String roleName;
    private String roleDescription;

    Role(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
}
