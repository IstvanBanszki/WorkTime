package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class Department {

    private final int id;
    private final String name;
    private final LocalDateTime dateOfRegistration;
    private final LocalDateTime dateOfFoundtation;

    public Department(int id, String name, LocalDateTime dateOfRegistration, LocalDateTime dateOfFoundtation) {
        this.id = id;
        this.name = name;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfFoundtation = dateOfFoundtation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public LocalDateTime getDateOfFoundtation() {
        return dateOfFoundtation;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", dateOfRegistration="
                + dateOfRegistration + ", dateOfFoundtation=" + dateOfFoundtation + '}';
    }
}
