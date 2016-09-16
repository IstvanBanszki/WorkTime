package hu.unideb.worktime.api.model.addition;

import hu.unideb.worktime.api.model.administration.Employee;
import java.io.Serializable;

public class Superior extends Employee implements Serializable {
    
    private final int id;

    public Superior() {
        super();
        this.id = 0;
    }

    public Superior(int id, String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + this.id;
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
        if(! super.equals(obj)) {
            return false;
        }
        final Superior other = (Superior) obj;
        return (this.id == other.id);
    }

    @Override
    public String toString() {
        return "Superior{id=" + id + ", firstName=" + this.getFirstName() + 
                ", lastName=" + this.getLastName() + '}';
    }
    
}
