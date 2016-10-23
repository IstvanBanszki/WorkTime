package hu.unideb.worktime.api.model;

import java.util.Arrays;
import java.util.Optional;

public enum WorkStatus {

    NOT_SET(0),
    WAITING(1),
    APPROVE(2);

    private WorkStatus(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return this.id;
    }
    
    public static WorkStatus valueOf(int id){
        Optional<WorkStatus> g = Arrays.stream(WorkStatus.values()).filter(workStatus -> workStatus.getId() == id).findFirst();
        if( g.isPresent() ){
            return g.get();
        } else {
            return WorkStatus.NOT_SET;
        }
    }

}
