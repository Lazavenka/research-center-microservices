package com.roger.researchcenter.model;

import java.util.Arrays;
import java.util.Optional;

public enum Roles {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private final String roleName;

    Roles(String roleName){
        this.roleName = roleName;
    }

    public String getValue() {
        return this.roleName;
    }

    public static Roles getRoleByName(String roleName) {
        Optional<Roles> managerDegree = Arrays.stream(Roles.values()).filter(d -> roleName.equalsIgnoreCase(d.getValue())).findAny();
        return managerDegree.orElseThrow(() -> new IllegalArgumentException("Role " + roleName + " not found"));
    }
}
