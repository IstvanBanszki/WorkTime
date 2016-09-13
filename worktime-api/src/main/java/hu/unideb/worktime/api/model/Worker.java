package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class Worker {

    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDateTime dateOfBirth;
    private final String nationality;
    private final String position;
    private final int dailyWorkHourTotal;
    private final String emailAddres;
    private final int superiorId;
    private final int departmentId;
    private final int userId;

    public Worker(String firstName, String lastName, Gender gender, LocalDateTime dateOfBirth, 
            String nationality, String position, int dailyWorkHourTotal, String emailAddres, 
            int superiorId, int departmentId, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.position = position;
        this.dailyWorkHourTotal = dailyWorkHourTotal;
        this.emailAddres = emailAddres;
        this.superiorId = superiorId;
        this.departmentId = departmentId;
        this.userId = userId;
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

    public int getDailyWorkHourTotal() {
        return dailyWorkHourTotal;
    }

    public String getEmailAddres() {
        return emailAddres;
    }

    public int getSuperiorId() {
        return superiorId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 59 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 59 * hash + (this.gender != null ? this.gender.hashCode() : 0);
        hash = 59 * hash + (this.dateOfBirth != null ? this.dateOfBirth.hashCode() : 0);
        hash = 59 * hash + (this.nationality != null ? this.nationality.hashCode() : 0);
        hash = 59 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 59 * hash + this.dailyWorkHourTotal;
        hash = 59 * hash + (this.emailAddres != null ? this.emailAddres.hashCode() : 0);
        hash = 59 * hash + this.superiorId;
        hash = 59 * hash + this.departmentId;
        hash = 59 * hash + this.userId;
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
        final Worker other = (Worker) obj;
        return (this.dailyWorkHourTotal != other.dailyWorkHourTotal) &&
               (this.superiorId != other.superiorId)  &&
               (this.departmentId != other.departmentId) &&
               (this.userId != other.userId) &&
               (this.firstName != null ? this.firstName.equals(other.firstName) : other.firstName == null) &&
               (this.lastName != null ? this.lastName.equals(other.lastName) : other.lastName == null) &&
               (this.dateOfBirth != null ? this.dateOfBirth.equals(other.dateOfBirth) : other.dateOfBirth == null) &&
               (this.nationality != null ? this.nationality.equals(other.nationality) : other.nationality == null) &&
               (this.position != null ? this.position.equals(other.position) : other.position == null) &&
               (this.emailAddres != null ? this.emailAddres.equals(other.emailAddres) : other.emailAddres == null) &&
               (this.gender != null ? this.gender.equals(other.gender) : other.gender == null);
    }

    @Override
    public String toString() {
        return "Worker{firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + 
                ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality + 
                ", position=" + position + ", dailyWorkHourTotal=" + dailyWorkHourTotal + 
                ", emailAddres=" + emailAddres + ", superiorId=" + superiorId + 
                ", departmentId=" + departmentId + ", userId=" + userId + '}';
    }

}
