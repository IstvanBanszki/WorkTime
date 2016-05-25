package hu.unideb.worktime.api.model.profile;

import hu.unideb.worktime.api.model.Gender;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProfileRecord {
    
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

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public String getOfficeName() {
        return officeName;
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
              (this.gender != null ? this.gender.equals(other.gender) : other.gender == null) &&
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
        return "ProfileRecord{" + "dateOfRegistration=" + dateOfRegistration + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality + ", position=" + position + ", emailAddress=" + emailAddress + ", dailyWorkHourTotal=" + dailyWorkHourTotal + ", departmentName=" + departmentName + ", officeName=" + officeName + '}';
    }
}
