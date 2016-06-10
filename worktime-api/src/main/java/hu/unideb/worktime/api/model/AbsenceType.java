package hu.unideb.worktime.api.model;

import java.util.Arrays;

public enum AbsenceType {
    
    NOT_SET(1),
    PAYED(2),
    UNPAYED(3),
    SICK_PAY(4),
    VERIFIED(5);

    private AbsenceType(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return this.id;
    }
    
    public static AbsenceType valueOf(int id) {
        return Arrays.stream(AbsenceType.values()).filter(status -> status.getId() == id).findFirst().orElse(NOT_SET);
    }
}
