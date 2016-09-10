package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class EditWorkerRequest implements Serializable {
    
    private final String firstName;
    private final String lastName;
    private final String position;
    private final String emailAddress;
    private final int dailyWorkHourTotal;

    public EditWorkerRequest(String firstName, String lastName, String position, String emailAddress, int dailyWorkHourTotal) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.emailAddress = emailAddress;
        this.dailyWorkHourTotal = dailyWorkHourTotal;
    }
    
    public EditWorkerRequest() {
        this("","","","",0);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getDailyWorkHourTotal() {
        return dailyWorkHourTotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 97 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 97 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 97 * hash + (this.emailAddress != null ? this.emailAddress.hashCode() : 0);
        hash = 97 * hash + this.dailyWorkHourTotal;
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
        final EditWorkerRequest other = (EditWorkerRequest) obj;
        return (this.dailyWorkHourTotal == other.dailyWorkHourTotal) && 
                (this.firstName != null ? this.firstName.equals(other.firstName) : other.firstName == null) && 
                (this.lastName != null ? this.lastName.equals(other.lastName) : other.lastName == null) && 
                (this.position != null ? this.position.equals(other.position) : other.position == null) && 
                (this.emailAddress != null ? this.emailAddress.equals(other.emailAddress) : other.emailAddress == null);
    }

    @Override
    public String toString() {
        return "EditWorkerRequest{first_name=" + this.firstName + ", last_name=" + this.lastName + 
                ", position=" + this.position + ", email_address=" + this.emailAddress +
                ", daily_work_hour_total=" + this.dailyWorkHourTotal + '}';
    }
    
}
