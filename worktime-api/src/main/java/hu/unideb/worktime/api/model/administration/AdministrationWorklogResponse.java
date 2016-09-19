package hu.unideb.worktime.api.model.administration;

import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdministrationWorklogResponse extends WorklogResponse implements Serializable {
    
    private final LocalDateTime dateOfRegistration;
    private final LocalDateTime dateOfModification;
    private final String note;

    public AdministrationWorklogResponse(LocalDateTime dateOfRegistration, LocalDateTime dateOfModification, 
            String note, int id, LocalDate beginDate, int workHour) {
        super(id, beginDate, workHour);
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfModification = dateOfModification;
        this.note = note;
    }

    public String getNote() {
        return this.note;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public LocalDateTime getDateOfModification() {
        return dateOfModification;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + (this.note != null ? this.note.hashCode() : 0);
        hash = 97 * hash + (this.dateOfRegistration != null ? this.dateOfRegistration.hashCode() : 0);
        hash = 97 * hash + (this.dateOfModification != null ? this.dateOfModification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        if(! super.equals(obj)) {
            return false;
        }
        final AdministrationWorklogResponse other = (AdministrationWorklogResponse) obj;
        return (this.note != null ? this.note.equals(other.note) : other.note == null) &&
               (this.dateOfRegistration != null ? this.dateOfRegistration.equals(other.dateOfRegistration) : other.dateOfRegistration == null) &&
               (this.dateOfModification != null ? this.dateOfModification.equals(other.dateOfModification) : other.dateOfModification == null);
    }
    

    @Override
    public String toString() {
        return "AdministrationWorklogResponse{id=" + this.getId() + ", beginDate=" + this.getBeginDate() + 
                ", workHour=" + this.getWorkHour() + ", dateOfRegistration=" + this.dateOfRegistration + 
                ", dateOfModification=" + this.dateOfModification + ", note=" + this.note + '}';
    }
}
