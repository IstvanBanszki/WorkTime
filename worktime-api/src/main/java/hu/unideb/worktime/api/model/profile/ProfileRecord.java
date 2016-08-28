package hu.unideb.worktime.api.model.profile;

import hu.unideb.worktime.api.model.Gender;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ProfileRecord implements Serializable {
    
    private final LocalDateTime dateOfRegistration;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDateTime dateOfBirth;
    private final String nationality;
    private final String position;
    private final String emailAddress;
    private final int dailyWorkHourTotal;
    private final String departmentName;
    private final String officeName;

    public ProfileRecord(LocalDateTime dateOfRegistration, String firstName, String lastName, Gender gender, LocalDateTime dateOfBirth, String nationality, String position, String emailAddress, int dailyWorkHourTotal, String departmentName, String officeName) {
        this.dateOfRegistration = dateOfRegistration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.position = position;
        this.emailAddress = emailAddress;
        this.dailyWorkHourTotal = dailyWorkHourTotal;
        this.departmentName = departmentName;
        this.officeName = officeName;
    }

    public static class ProfileRecordBuilder {
        
        private LocalDateTime dateOfRegistration;
        private String firstName;
        private String lastName;
        private Gender gender;
        private LocalDateTime dateOfBirth;
        private String nationality;
        private String position;
        private String emailAddress;
        private int dailyWorkHourTotal;
        private String departmentName;
        private String officeName;

        public ProfileRecordBuilder() {
            this.dateOfRegistration = null;
            this.firstName = "";
            this.lastName = "";
            this.gender = Gender.NOT_SET;
            this.dateOfBirth = null;
            this.nationality = "";
            this.position = "";
            this.emailAddress = "";
            this.dailyWorkHourTotal = 0;
            this.departmentName = "";
            this.officeName = "";
        }

        public ProfileRecordBuilder setDateOfRegistration(LocalDateTime dateOfRegistration) {
            this.dateOfRegistration = dateOfRegistration;
            return this;
        }

        public ProfileRecordBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ProfileRecordBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ProfileRecordBuilder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public ProfileRecordBuilder setDateOfBirth(LocalDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public ProfileRecordBuilder setNationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public ProfileRecordBuilder setPosition(String position) {
            this.position = position;
            return this;
        }

        public ProfileRecordBuilder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public ProfileRecordBuilder setDailyWorkHourTotal(int dailyWorkHourTotal) {
            this.dailyWorkHourTotal = dailyWorkHourTotal;
            return this;
        }

        public ProfileRecordBuilder setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public ProfileRecordBuilder setOfficeName(String officeName) {
            this.officeName = officeName;
            return this;
        }
        
        public ProfileRecord build(){
            return new ProfileRecord(this.dateOfRegistration,
                                     this.firstName,
                                     this.lastName,
                                     this.gender,
                                     this.dateOfBirth,
                                     this.nationality,
                                     this.position,
                                     this.emailAddress,
                                     this.dailyWorkHourTotal,
                                     this.departmentName,
                                     this.officeName);
        }
    }

    public LocalDateTime getDateOfRegistration() {
        return this.dateOfRegistration;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public LocalDateTime getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getPosition() {
        return this.position;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public int getDailyWorkHourTotal() {
        return this.dailyWorkHourTotal;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public String getOfficeName() {
        return this.officeName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.dateOfRegistration != null ? this.dateOfRegistration.hashCode() : 0);
        hash = 31 * hash + (this.firstName != null ? this.dateOfRegistration.hashCode() : 0);
        hash = 31 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 31 * hash + (this.gender != null ? this.gender.getId(): 0);
        hash = 31 * hash + (this.dateOfBirth != null ? this.dateOfBirth.hashCode() : 0);
        hash = 31 * hash + (this.nationality != null ? this.nationality.hashCode() : 0);
        hash = 31 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 31 * hash + (this.emailAddress != null ? this.emailAddress.hashCode() : 0);
        hash = 31 * hash + (this.dateOfRegistration != null ? this.dateOfRegistration.hashCode() : 0);
        hash = 31 * hash + this.dailyWorkHourTotal;
        hash = 31 * hash + (this.departmentName != null ? this.departmentName.hashCode() : 0);
        hash = 31 * hash + (this.officeName != null ? this.officeName.hashCode() : 0);
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
        final ProfileRecord other = (ProfileRecord) obj;
        return this.dailyWorkHourTotal != other.dailyWorkHourTotal &&
              (this.dateOfRegistration != null ? this.dateOfRegistration.equals(other.dateOfRegistration) : other.dateOfRegistration == null) &&
              (this.firstName != null ? this.firstName.equals(other.firstName) : other.firstName == null) &&
              (this.lastName != null ? this.lastName.equals(other.lastName) : other.lastName == null) &&
              (this.gender == other.gender) &&
              (this.dateOfBirth != null ? this.dateOfBirth.equals(other.dateOfBirth) : other.dateOfBirth == null) &&
              (this.nationality != null ? this.nationality.equals(other.nationality) : other.nationality == null) &&
              (this.position != null ? this.position.equals(other.position) : other.position == null) &&
              (this.emailAddress != null ? this.emailAddress.equals(other.emailAddress) : other.emailAddress == null) &&
              (this.dateOfRegistration != null ? this.dateOfRegistration.equals(other.dateOfRegistration) : other.dateOfRegistration == null) &&
              (this.departmentName != null ? this.departmentName.equals(other.departmentName) : other.departmentName == null) &&
              (this.officeName != null ? this.officeName.equals(other.officeName) : other.officeName == null);
    }

    @Override
    public String toString() {
        return "ProfileRecord{" + "dateOfRegistration=" + this.dateOfRegistration + 
                ", firstName=" + this.firstName + ", lastName=" + this.lastName +
                ", gender=" + this.gender + ", dateOfBirth=" + this.dateOfBirth + 
                ", nationality=" + this.nationality + ", position=" + this.position + 
                ", emailAddress=" + this.emailAddress + ", dailyWorkHourTotal=" + this.dailyWorkHourTotal + 
                ", departmentName=" + this.departmentName + ", officeName=" + this.officeName + '}';
    }
}
