package hu.unideb.worktime.api.model.absence;

public class AbsenceDataResponse {
    
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

    public static class AbsenceDataResponseBuilder {

        private int year;
        private int holidayNumber;
        private int absenceNumber;
        private int notSetAbsenceNumber;
        private int payedAbsenceNumber;
        private int unPayedAbsenceNumber;
        private int sickPayedAbsenceNumber;
        private int verifiedAbsenceNumber;

        public AbsenceDataResponseBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public AbsenceDataResponseBuilder setHolidayNumber(int holidayNumber) {
            this.holidayNumber = holidayNumber;
            return this;
        }

        public AbsenceDataResponseBuilder setAbsenceNumber(int absenceNumber) {
            this.absenceNumber = absenceNumber;
            return this;
        }

        public AbsenceDataResponseBuilder setNotSetAbsenceNumber(int notSetAbsenceNumber) {
            this.notSetAbsenceNumber = notSetAbsenceNumber;
            return this;
        }

        public AbsenceDataResponseBuilder setPayedAbsenceNumber(int payedAbsenceNumber) {
            this.payedAbsenceNumber = payedAbsenceNumber;
            return this;
        }

        public AbsenceDataResponseBuilder setUnPayedAbsenceNumber(int unPayedAbsenceNumber) {
            this.unPayedAbsenceNumber = unPayedAbsenceNumber;
            return this;
        }

        public AbsenceDataResponseBuilder setSickPayedAbsenceNumber(int sickPayedAbsenceNumber) {
            this.sickPayedAbsenceNumber = sickPayedAbsenceNumber;
            return this;
        }

        public AbsenceDataResponseBuilder setVerifiedAbsenceNumber(int verifiedAbsenceNumber) {
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
        return year;
    }

    public int getHolidayNumber() {
        return holidayNumber;
    }

    public int getAbsenceNumber() {
        return absenceNumber;
    }

    public int getNotSetAbsenceNumber() {
        return notSetAbsenceNumber;
    }

    public int getPayedAbsenceNumber() {
        return payedAbsenceNumber;
    }

    public int getUnPayedAbsenceNumber() {
        return unPayedAbsenceNumber;
    }

    public int getSickPayedAbsenceNumber() {
        return sickPayedAbsenceNumber;
    }

    public int getVerifiedAbsenceNumber() {
        return verifiedAbsenceNumber;
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
        return "AbsenceDataResponse{year=" + year + ", holidayNumber=" + holidayNumber + 
               ", absenceNumber=" + absenceNumber + ", notSetAbsenceNumber=" + notSetAbsenceNumber + 
               ", payedAbsenceNumber=" + payedAbsenceNumber + ", unPayedAbsenceNumber=" + unPayedAbsenceNumber + 
               ", sickPayedAbsenceNumber=" + sickPayedAbsenceNumber + ", verifiedAbsenceNumber=" + verifiedAbsenceNumber + '}';
    }
    
}
