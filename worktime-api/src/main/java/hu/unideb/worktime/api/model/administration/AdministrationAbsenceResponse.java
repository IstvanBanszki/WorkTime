package hu.unideb.worktime.api.model.administration;

import hu.unideb.worktime.api.model.AbsenceType;
import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import java.io.Serializable;
import java.time.LocalDate;

public class AdministrationAbsenceResponse extends AbsenceResponse implements Serializable {
    
    private final LocalDate dateOfRegistration;
    private final LocalDate dateOfModification;
    private final String note;

    public AdministrationAbsenceResponse(LocalDate dateOfRegistration, LocalDate dateOfModification, String note, int id, LocalDate beginDate, 
            LocalDate endDate, Status status, AbsenceType absenceType) {
        super(id, beginDate, endDate, status, absenceType);
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfModification = dateOfModification;
        this.note = note;
    }

    public static class Builder {

        private int id;
        private LocalDate beginDate;
        private LocalDate endDate;
        private Status status;
        private AbsenceType absenceType;
        private LocalDate dateOfRegistration;
        private LocalDate dateOfModification;
        private String note;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setBeginDate(LocalDate begin) {
            this.beginDate = begin;
            return this;
        }

        public Builder setEndDate(LocalDate end) {
            this.endDate = end;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setAbsenceType(AbsenceType absenceType) {
            this.absenceType = absenceType;
            return this;
        }

        public Builder setDateOfRegistration(LocalDate dateOfRegistration) {
            this.dateOfRegistration = dateOfRegistration;
            return this;
        }

        public Builder setDateOfModification(LocalDate dateOfModification) {
            this.dateOfModification = dateOfModification;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public AdministrationAbsenceResponse build() {
            return new AdministrationAbsenceResponse(this.dateOfRegistration, this.dateOfModification, 
                    this.note, this.id, this.beginDate, this.endDate, this.status, this.absenceType);
        }
    }    

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public LocalDate getDateOfModification() {
        return dateOfModification;
    }

    public String getNote() {
        return this.note;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 67 * hash + (this.note != null ? this.note.hashCode() : 0);
        hash = 67 * hash + (this.dateOfRegistration != null ? this.dateOfRegistration.hashCode() : 0);
        hash = 67 * hash + (this.dateOfModification != null ? this.dateOfModification.hashCode() : 0);
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
        if(! super.equals(obj)) {
            return false;
        }
        final AdministrationAbsenceResponse other = (AdministrationAbsenceResponse) obj;
        return (this.note != null ? this.note.equals(other.note) : other.note == null) &&
               (this.dateOfRegistration != null ? this.dateOfRegistration.equals(other.dateOfRegistration) : other.dateOfRegistration == null) &&
               (this.dateOfModification != null ? this.dateOfModification.equals(other.dateOfModification) : other.dateOfModification == null);
    }

    @Override
    public String toString() {
        return "AdministrationAbsenceResponse{id=" + this.getId() + ", beginDate=" + this.getBeginDate() + 
                ", endDate=" + this.getEndDate() + ", status=" + this.getStatus() + 
                ", absenceType=" + this.getAbsenceType() + ", dateOfRegistration=" + this.dateOfRegistration + 
                ", dateOfModification=" + this.dateOfModification + ", note=" + this.note + '}';
    }
}
