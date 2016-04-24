package hu.unideb.worktime.api.model;

public enum Gender {

    NOT_SET(0),
    MALE(1),
    FEMALE(2);

    private Gender(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return this.id;
    }
}
