package hu.unideb.worktime.api.model.administration;

import hu.unideb.worktime.api.model.AbsenceType;
import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AdministrationAbsenceResponse extends AbsenceResponse implements Serializable {
    
    private final String note;

    public AdministrationAbsenceResponse(String note, int id, LocalDateTime beginDate, LocalDateTime endDate, Status status, AbsenceType absenceType) {
        super(id, beginDate, endDate, status, absenceType);
        this.note = note;
    }

    public static class AdministrationAbsenceResponseBuilder {

        private int id;
        private LocalDateTime beginDate;
        private LocalDateTime endDate;
        private Status status;
        private AbsenceType absenceType;
        private String note;

        public AdministrationAbsenceResponseBuilder() {
            this.beginDate = null;
            this.endDate = null;
            this.status = Status.NOT_SET;
        }

        public AdministrationAbsenceResponseBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AdministrationAbsenceResponseBuilder setBeginDate(LocalDateTime begin) {
            this.beginDate = begin;
            return this;
        }

        public AdministrationAbsenceResponseBuilder setEndDate(LocalDateTime end) {
            this.endDate = end;
            return this;
        }

        public AdministrationAbsenceResponseBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public AdministrationAbsenceResponseBuilder setAbsenceType(AbsenceType absenceType) {
            this.absenceType = absenceType;
            return this;
        }

        public AdministrationAbsenceResponseBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public AdministrationAbsenceResponse build() {
            return new AdministrationAbsenceResponse(this.note, this.id, this.beginDate, this.endDate, this.status, this.absenceType);
        }
    }    

    public String getNote() {
        return this.note;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 67 * hash + (this.note != null ? this.note.hashCode() : 0);
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
        return (this.note != null ? this.note.equals(other.note) : other.note == null);
    }

    @Override
    public String toString() {
        return "AdministrationAbsenceResponse{id=" + this.getId() + ", beginDate=" + this.getBeginDate() + 
                ", endDate=" + this.getEndDate() + ", status=" + this.getStatus() + 
                ", absenceType=" + this.getAbsenceType() + ", note=" + this.note + '}';
    }
    
}
