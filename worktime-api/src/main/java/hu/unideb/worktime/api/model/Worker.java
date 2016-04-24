package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class Worker {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDateTime dateOfBirth;
    private final String nationality;
    private final String position;
    private final int dailyWorkHourTotal;
    private final String emailAddres;
    private final int superiorId;

    private Worker(int id, String firstName, String lastName, Gender gender, LocalDateTime dateOfBirth, String nationality, String position, int dailyWorkHourTotal, String emailAddres, int superiorId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.position = position;
        this.dailyWorkHourTotal = dailyWorkHourTotal;
        this.emailAddres = emailAddres;
        this.superiorId = superiorId;
    }

    public static class WorkerBuilder {

        private int id;
        private String firstName;
        private String lastName;
        private Gender gender;
        private LocalDateTime dateOfBirth;
        private String nationality;
        private String position;
        private int dailyWorkHourTotal;
        private String emailAddres;
        private int superiorId;

        public WorkerBuilder() {
            this.id = 0;
            this.firstName = "";
            this.lastName = "";
            this.gender = Gender.NOT_SET;
            this.dateOfBirth = null;
            this.nationality = "";
            this.position = "";
            this.dailyWorkHourTotal = 0;
            this.emailAddres = "";
            this.superiorId = 0;
        }

        public WorkerBuilder id(int id) {
            this.id = id;
            return this;
        }

        public WorkerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public WorkerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public WorkerBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public WorkerBuilder dateOfBirth(LocalDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public WorkerBuilder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public WorkerBuilder position(String position) {
            this.position = position;
            return this;
        }

        public WorkerBuilder dailyWorkHourTotal(int dailyWorkHourTotal) {
            this.dailyWorkHourTotal = dailyWorkHourTotal;
            return this;
        }

        public WorkerBuilder emailAddres(String emailAddres) {
            this.emailAddres = emailAddres;
            return this;
        }

        public WorkerBuilder superiorId(int superiorId) {
            this.superiorId = superiorId;
            return this;
        }

        public Worker build() {
            return new Worker(this.id, this.firstName, this.lastName, this.gender,
                    this.dateOfBirth, this.nationality, this.position, this.dailyWorkHourTotal,
                    this.emailAddres, this.superiorId);
        }
    }

    public int getId() {
        return id;
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

}
