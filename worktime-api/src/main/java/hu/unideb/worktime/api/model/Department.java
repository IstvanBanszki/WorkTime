package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class Department {

    private final String name;
    private final LocalDateTime dateOfFoundtation;
    private final int officeId;

    public Department(String name, LocalDateTime dateOfFoundtation, int officeId) {
        this.name = name;
        this.dateOfFoundtation = dateOfFoundtation;
        this.officeId = officeId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateOfFoundtation() {
        return dateOfFoundtation;
    }

    public int getOfficeId() {
        return officeId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.dateOfFoundtation != null ? this.dateOfFoundtation.hashCode() : 0);
        hash = 53 * hash + this.officeId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        return (this.officeId == other.officeId) &&
               (this.name != null ? this.name.equals(other.name) : other.name == null) &&
               (this.dateOfFoundtation!= null ? this.dateOfFoundtation.equals(other.dateOfFoundtation) : other.dateOfFoundtation == null);
    }

    @Override
    public String toString() {
        return "Department{name=" + name + ", dateOfFoundtation=" + dateOfFoundtation + ", officeId=" + officeId + '}';
    }

}
