package hu.unideb.worktime.api.model;

public enum Role {

    NOT_SET(0),
    WORKER_ROLE(1),
    SUPEROR_ROLE(2),
    COMPANY_ADMIN_ROLE(3);

    private Role(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return this.id;
    }
}
