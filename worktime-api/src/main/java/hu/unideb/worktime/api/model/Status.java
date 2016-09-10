package hu.unideb.worktime.api.model;

import java.util.Arrays;

public enum Status {

    NOT_SET(1, "Not Set"),
    NOT_APPROVE(2, "Not Approve"),
    APPROVE(3, "Approve");

    private Status(int id, String name) {
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
    
    public static Status valueOf(int id){
        return Arrays.stream(Status.values()).filter(status -> status.getId() == id).findFirst().orElse(NOT_SET);
    }
}
