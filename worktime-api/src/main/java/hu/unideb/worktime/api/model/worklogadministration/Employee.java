package hu.unideb.worktime.api.model.worklogadministration;

import java.io.Serializable;

public class Employee implements Serializable {
    
    private final String firstName;
    private final String lastName;

    public Employee() {
        this.firstName = "";
        this.lastName = "";
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
               (this.lastName != null ? this.lastName.equals(other.lastName) : other.lastName == null);
    }

    @Override
    public String toString() {
        return "Employee{firstName=" + firstName + ", lastName=" + lastName + '}';
    }
}
