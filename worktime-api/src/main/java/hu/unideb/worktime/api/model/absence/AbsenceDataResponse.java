package hu.unideb.worktime.api.model.absence;

import java.io.Serializable;

public class AbsenceDataResponse implements Serializable {
    
    private final int year;
    private final int holidayNumber;
    private final int absenceNumber;
    private final int notSetNumber;
    private final int payedNumber;
    private final int unPayedNumber;
    private final int sickPayedNumber;
    private final int verifiedNumber;

    public AbsenceDataResponse(int year, int holidayNumber, int absenceNumber, int notSetAbsenceNumber, 
                               int payedAbsenceNumber, int unPayedAbsenceNumber, int sickPayedAbsenceNumber, 
                               int verifiedAbsenceNumber) {
        this.year = year;
        this.holidayNumber = holidayNumber;
        this.absenceNumber = absenceNumber;
        this.notSetNumber = notSetAbsenceNumber;
        this.payedNumber = payedAbsenceNumber;
        this.unPayedNumber = unPayedAbsenceNumber;
        this.sickPayedNumber = sickPayedAbsenceNumber;
        this.verifiedNumber = verifiedAbsenceNumber;
    }

    public static class Builder {

        private int year;
        private int holidayNumber;
        private int absenceNumber;
        private int notSetNumber;
        private int payedNumber;
        private int unPayedNumber;
        private int sickPayedNumber;
        private int verifiedNumber;

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

        public Builder setNotSetNumber(int notSetNumber) {
            this.notSetNumber = notSetNumber;
            return this;
        }

        public Builder setPayedNumber(int payedNumber) {
            this.payedNumber = payedNumber;
            return this;
        }

        public Builder setUnPayedNumber(int unPayedNumber) {
            this.unPayedNumber = unPayedNumber;
            return this;
        }

        public Builder setSickPayedNumber(int sickPayedNumber) {
            this.sickPayedNumber = sickPayedNumber;
            return this;
        }

        public Builder setVerifiedNumber(int verifiedNumber) {
            this.verifiedNumber = verifiedNumber;
            return this;
        }

        public AbsenceDataResponse build() {
            return new AbsenceDataResponse(this.year, this.holidayNumber, this.absenceNumber, this.notSetNumber, 
                                           this.payedNumber, this.unPayedNumber, this.sickPayedNumber, 
                                           this.verifiedNumber);
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

    public int getNotSetNumber() {
        return this.notSetNumber;
    }

    public int getPayedNumber() {
        return this.payedNumber;
    }

    public int getUnPayedNumber() {
        return this.unPayedNumber;
    }

    public int getSickPayedNumber() {
        return this.sickPayedNumber;
    }

    public int getVerifiedNumber() {
        return this.verifiedNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.year;
        hash = 47 * hash + this.holidayNumber;
        hash = 47 * hash + this.absenceNumber;
        hash = 47 * hash + this.notSetNumber;
        hash = 47 * hash + this.payedNumber;
        hash = 47 * hash + this.unPayedNumber;
        hash = 47 * hash + this.sickPayedNumber;
        hash = 47 * hash + this.verifiedNumber;
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
               (this.notSetNumber == other.notSetNumber) &&
               (this.payedNumber == other.payedNumber) &&
               (this.unPayedNumber == other.unPayedNumber) &&
               (this.sickPayedNumber == other.sickPayedNumber) &&
               (this.verifiedNumber == other.verifiedNumber);
    }

    @Override
    public String toString() {
        return "AbsenceDataResponse{year=" + this.year + ", holidayNumber=" + this.holidayNumber + 
               ", absenceNumber=" + this.absenceNumber + ", notSetNumber=" + this.notSetNumber + 
               ", payedNumber=" + this.payedNumber + ", unPayedNumber=" + this.unPayedNumber + 
               ", sickPayedNumber=" + this.sickPayedNumber + ", verifiedNumber=" + this.verifiedNumber + '}';
    }
    
}
