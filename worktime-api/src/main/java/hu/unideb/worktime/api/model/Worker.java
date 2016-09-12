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

    private Worker(String firstName, String lastName, Gender gender, LocalDateTime dateOfBirth, 
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
    public String toString() {
        return "Worker{firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + 
                ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality + 
                ", position=" + position + ", dailyWorkHourTotal=" + dailyWorkHourTotal + 
                ", emailAddres=" + emailAddres + ", superiorId=" + superiorId + 
                ", departmentId=" + departmentId + ", userId=" + userId + '}';
    }

}
