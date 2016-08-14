package hu.unideb.worktime.api.model.absence;

public class AbsenceDataResponse {
    
    private final int year;
    private final int holidayNumber;
    private final int absenceNumber;

    public AbsenceDataResponse(int year, int holidayNumber, int absenceNumber) {
        this.year = year;
        this.holidayNumber = holidayNumber;
        this.absenceNumber = absenceNumber;
    }

    public int getYear() {
        return year;
    }

    public int getHolidayNumber() {
        return holidayNumber;
    }

    public int getAbsenceNumber() {
        return absenceNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.year;
        hash = 29 * hash + this.holidayNumber;
        hash = 29 * hash + this.absenceNumber;
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
        final AbsenceDataResponse other = (AbsenceDataResponse) obj;
        return (other.year == this.year) &&
               (other.holidayNumber == this.holidayNumber) &&
               (other.absenceNumber == this.absenceNumber);
    }

    @Override
    public String toString() {
        return "AbsenceDataResponse{year=" + year + ", holidayNumber=" + holidayNumber + ", absenceNumber=" + absenceNumber + '}';
    }           
}
