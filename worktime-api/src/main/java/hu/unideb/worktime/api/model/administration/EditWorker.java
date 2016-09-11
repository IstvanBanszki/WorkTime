package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class EditWorker implements Serializable {
    
    private final String firstName;
    private final String lastName;
    private final String position;
    private final String emailAddress;
    private final int dailyWorkHourTotal;

    public EditWorker(String firstName, String lastName, String position, String emailAddress, int dailyWorkHourTotal) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.emailAddress = emailAddress;
        this.dailyWorkHourTotal = dailyWorkHourTotal;
    }
    
    public EditWorker() {
        this("","","","",0);
    }
    
    public static class Builder {
        
        private String firstName;
        private String lastName;
        private String position;
        private String emailAddress;
        private int dailyWorkHourTotal;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setDailyWorkHourTotal(int dailyWorkHourTotal) {
            this.dailyWorkHourTotal = dailyWorkHourTotal;
            return this;
        }
        
        public EditWorker build() {
            return new EditWorker(this.firstName, this.lastName, this.position, this.emailAddress, this.dailyWorkHourTotal);
        }
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
        final EditWorker other = (EditWorker) obj;
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
