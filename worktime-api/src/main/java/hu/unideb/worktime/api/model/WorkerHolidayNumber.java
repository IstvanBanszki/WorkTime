package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class WorkerHolidayNumber {

    private final int id;
    private final int year;
    private final int holidayNumberTotal;
    private final LocalDateTime dateOfRegistration;

    public WorkerHolidayNumber(int id, int year, int holidayNumberTotal, LocalDateTime dateOfRegistration) {
        this.id = id;
        this.year = year;
        this.holidayNumberTotal = holidayNumberTotal;
        this.dateOfRegistration = dateOfRegistration;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getHolidayNumberTotal() {
        return holidayNumberTotal;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    @Override
    public String toString() {
        return "WorkerHolidayNumber{" + "id=" + id + ", year=" + year + ", holidayNumberTotal="
                + holidayNumberTotal + ", dateOfRegistration=" + dateOfRegistration + '}';
    }
}
