package hu.unideb.worktime.api.model;

import java.util.Arrays;
import java.util.Optional;

public enum Status {

    NOT_SET(0),
    NOT_APPROVE(1),
    APPROVE(2);

    private Status(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return this.id;
    }
    
    public static Status valueOf(int id){
        Optional<Status> s = Arrays.stream(Status.values()).filter(status -> status.getId() == id).findFirst();
        if( s.isPresent() ){
            return s.get();
        } else {
            throw new IllegalStateException("No valid Status enum found for the id: " + id);
        }
    }

    
}
