package hu.unideb.worktime.api.model;

public enum Role {

    NOT_SET(1, "NOT-SET"),
    WORKER_ROLE(2, "COMPANY-ADMIN-ROLE"),
    SUPEROR_ROLE(3, "SUPERIOR-ROLE"),
    COMPANY_ADMIN_ROLE(4, "WORKER-ROLE");

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
}
