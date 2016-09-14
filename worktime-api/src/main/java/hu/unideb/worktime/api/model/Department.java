package hu.unideb.worktime.api.model;

import java.time.LocalDate;

public class Department {

    private final int id;
    private final String name;
    private final LocalDate dateOfFoundtation;
    private final int officeId;
    private final int workerNumber;

    public Department(int id, String name, LocalDate dateOfFoundtation, int officeId, int workerNumber) {
        this.id = id;
        this.name = name;
        this.dateOfFoundtation = dateOfFoundtation;
        this.officeId = officeId;
        this.workerNumber = workerNumber;
    }

    public Department() {
        this.id = 0;
        this.name = "";
        this.dateOfFoundtation = LocalDate.now();
        this.officeId = 0;
        this.workerNumber = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfFoundtation() {
        return dateOfFoundtation;
    }

    public int getOfficeId() {
        return officeId;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.dateOfFoundtation != null ? this.dateOfFoundtation.hashCode() : 0);
        hash = 53 * hash + this.officeId;
        hash = 53 * hash + this.workerNumber;
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
        return (this.id == other.id) &&
               (this.officeId == other.officeId) &&
               (this.workerNumber == other.workerNumber) &&
               (this.name != null ? this.name.equals(other.name) : other.name == null) &&
               (this.dateOfFoundtation!= null ? this.dateOfFoundtation.equals(other.dateOfFoundtation) : other.dateOfFoundtation == null);
    }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name=" + name + ", dateOfFoundtation=" + 
                dateOfFoundtation + ", officeId=" + officeId + ", workerNumber=" + workerNumber + '}';
    }
    
}
