package hu.unideb.worktime.api.model.absence;

import java.io.Serializable;

public class AbsenceDataResponse implements Serializable {
    
    private final int year;
    private final int holidayNumber;
    private final int absenceNumber;
    private final int notSetAbsenceNumber;
    private final int payedAbsenceNumber;
    private final int unPayedAbsenceNumber;
    private final int sickPayedAbsenceNumber;
    private final int verifiedAbsenceNumber;

    public AbsenceDataResponse(int year, int holidayNumber, int absenceNumber, int notSetAbsenceNumber, 
                               int payedAbsenceNumber, int unPayedAbsenceNumber, int sickPayedAbsenceNumber, 
                               int verifiedAbsenceNumber) {
        this.year = year;
        this.holidayNumber = holidayNumber;
        this.absenceNumber = absenceNumber;
        this.notSetAbsenceNumber = notSetAbsenceNumber;
        this.payedAbsenceNumber = payedAbsenceNumber;
        this.unPayedAbsenceNumber = unPayedAbsenceNumber;
        this.sickPayedAbsenceNumber = sickPayedAbsenceNumber;
        this.verifiedAbsenceNumber = verifiedAbsenceNumber;
    }

    public static class Builder {

        private int year;
        private int holidayNumber;
        private int absenceNumber;
        private int notSetAbsenceNumber;
        private int payedAbsenceNumber;
        private int unPayedAbsenceNumber;
        private int sickPayedAbsenceNumber;
        private int verifiedAbsenceNumber;

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setHolidayNumber(int holidayNumber) {
            this.holidayNumber = holidayNumber;
            return this;
        }

        public Builder setAbsenceNumber(int absenceNumber) {
            this.absenceNumber = absenceNumber;
            return this;
        }

        public Builder setNotSetAbsenceNumber(int notSetAbsenceNumber) {
            this.notSetAbsenceNumber = notSetAbsenceNumber;
            return this;
        }

        public Builder setPayedAbsenceNumber(int payedAbsenceNumber) {
            this.payedAbsenceNumber = payedAbsenceNumber;
            return this;
        }

        public Builder setUnPayedAbsenceNumber(int unPayedAbsenceNumber) {
            this.unPayedAbsenceNumber = unPayedAbsenceNumber;
            return this;
        }

        public Builder setSickPayedAbsenceNumber(int sickPayedAbsenceNumber) {
            this.sickPayedAbsenceNumber = sickPayedAbsenceNumber;
            return this;
        }

        public Builder setVerifiedAbsenceNumber(int verifiedAbsenceNumber) {
            this.verifiedAbsenceNumber = verifiedAbsenceNumber;
            return this;
        }

        public AbsenceDataResponse build() {
            return new AbsenceDataResponse(this.year, this.holidayNumber, this.absenceNumber, this.notSetAbsenceNumber, 
                                           this.payedAbsenceNumber, this.unPayedAbsenceNumber, this.sickPayedAbsenceNumber, 
                                           this.verifiedAbsenceNumber);
        }
    }

    public int getYear() {
        return this.year;
    }

    public int getHolidayNumber() {
        return this.holidayNumber;
    }

    public int getAbsenceNumber() {
        return this.absenceNumber;
    }

    public int getNotSetAbsenceNumber() {
        return this.notSetAbsenceNumber;
    }

    public int getPayedAbsenceNumber() {
        return this.payedAbsenceNumber;
    }

    public int getUnPayedAbsenceNumber() {
        return this.unPayedAbsenceNumber;
    }

    public int getSickPayedAbsenceNumber() {
        return this.sickPayedAbsenceNumber;
    }

    public int getVerifiedAbsenceNumber() {
        return this.verifiedAbsenceNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.year;
        hash = 47 * hash + this.holidayNumber;
        hash = 47 * hash + this.absenceNumber;
        hash = 47 * hash + this.notSetAbsenceNumber;
        hash = 47 * hash + this.payedAbsenceNumber;
        hash = 47 * hash + this.unPayedAbsenceNumber;
        hash = 47 * hash + this.sickPayedAbsenceNumber;
        hash = 47 * hash + this.verifiedAbsenceNumber;
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
        return (this.year == other.year) &&
               (this.holidayNumber == other.holidayNumber) &&
               (this.absenceNumber == other.absenceNumber) &&
               (this.notSetAbsenceNumber == other.notSetAbsenceNumber) &&
               (this.payedAbsenceNumber == other.payedAbsenceNumber) &&
               (this.unPayedAbsenceNumber == other.unPayedAbsenceNumber) &&
               (this.sickPayedAbsenceNumber == other.sickPayedAbsenceNumber) &&
               (this.verifiedAbsenceNumber == other.verifiedAbsenceNumber);
    }

    @Override
    public String toString() {
        return "AbsenceDataResponse{year=" + this.year + ", holidayNumber=" + this.holidayNumber + 
               ", absenceNumber=" + this.absenceNumber + ", notSetAbsenceNumber=" + this.notSetAbsenceNumber + 
               ", payedAbsenceNumber=" + this.payedAbsenceNumber + ", unPayedAbsenceNumber=" + this.unPayedAbsenceNumber + 
               ", sickPayedAbsenceNumber=" + this.sickPayedAbsenceNumber + ", verifiedAbsenceNumber=" + this.verifiedAbsenceNumber + '}';
    }
    
}
