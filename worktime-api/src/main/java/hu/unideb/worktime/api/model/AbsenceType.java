package hu.unideb.worktime.api.model;

import java.util.Arrays;

public enum AbsenceType {
    
    NOT_SET(1, "Not Set"),
    PAYED(2, "Payed"),
    UNPAYED(3, "UnPayed"),
    SICK_PAY(4, "SickPay"),
    VERIFIED(5, "Verified");

    private AbsenceType(int id, String name) {
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
    
    public static AbsenceType valueOf(int id) {
        return Arrays.stream(AbsenceType.values()).filter(status -> status.getId() == id).findFirst().orElse(NOT_SET);
    }
}
