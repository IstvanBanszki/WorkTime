package hu.unideb.worktime.api.model;

import java.time.LocalDate;

public class Office {

    private final int id;
    private final String name;
    private final String address;
    private final LocalDate dateOfFoundtation;

    public Office(int id, String name, String address, LocalDate dateOfFoundtation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfFoundtation = dateOfFoundtation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfFoundtation() {
        return dateOfFoundtation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 17 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 17 * hash + (this.dateOfFoundtation != null ? this.dateOfFoundtation.hashCode() : 0);
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
        final Office other = (Office) obj;
        return (this.id != other.id) &&
               (this.name != null ? this.name.equals(other.name) : other.name == null) &&
               (this.address != null ? this.address.equals(other.address) : other.address == null) &&
               (this.dateOfFoundtation!= null ? this.dateOfFoundtation.equals(other.dateOfFoundtation) : other.dateOfFoundtation == null);
    }

    @Override
    public String toString() {
        return "Office{id=" + id + ", name=" + name + ", address=" + address + ", dateOfFoundtation=" + dateOfFoundtation + '}';
    }

}
