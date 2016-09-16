package hu.unideb.worktime.api.model;

import java.util.Arrays;

public enum Role {

    NOT_SET(1, "NOT_SET"),
    WORKER_ROLE(2, "WORKER_ROLE"),
    SUPEROR_ROLE(3, "SUPERIOR_ROLE"),
    COMPANY_ADMIN_ROLE(4, "COMPANY_ADMIN_ROLE");

    private Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private final int id;
    private final String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    
    public static Role valueOf(int id){
        return Arrays.stream(Role.values()).filter(role -> role.getId() == id).findFirst().orElse(NOT_SET);
    }
}
