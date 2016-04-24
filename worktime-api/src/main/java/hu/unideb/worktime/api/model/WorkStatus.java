package hu.unideb.worktime.api.model;

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
}
