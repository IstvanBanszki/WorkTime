package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class Employee implements Serializable {
    
    private final int id;
    private final String firstName;
    private final String lastName;

    public Employee() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id);
        hash = 79 * hash + (this.firstName == null ? 0 : this.firstName.hashCode());
        hash = 79 * hash + (this.lastName == null ? 0 : this.lastName.hashCode());
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
        final Employee other = (Employee) obj;
        return (this.firstName != null ? this.firstName.equals(other.firstName) : other.firstName == null) &&
               (this.lastName != null ? this.lastName.equals(other.lastName) : other.lastName == null) &&
               (this.id == other.id);
    }

    @Override
    public String toString() {
        return "Employee{id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + '}';
    }
}
