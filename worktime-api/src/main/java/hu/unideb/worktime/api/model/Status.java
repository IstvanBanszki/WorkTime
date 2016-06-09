package hu.unideb.worktime.api.model;

import java.util.Arrays;

public enum Status {

    NOT_SET(1),
    NOT_APPROVE(2),
    APPROVE(3);

    private Status(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return this.id;
    }
    
    public static Status valueOf(int id){
        return Arrays.stream(Status.values()).filter(status -> status.getId() == id).findFirst().orElse(NOT_SET);
    }
}
