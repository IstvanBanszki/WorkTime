package hu.unideb.worktime.api.model.administration;

import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AdministrationWorklogResponse extends WorklogResponse implements Serializable {
    
    private final String note;

    public AdministrationWorklogResponse(String note, int id, LocalDateTime beginDate, int workHour) {
        super(id, beginDate, workHour);
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + (this.note != null ? this.note.hashCode() : 0);
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
        return (this.note != null ? this.note.equals(other.note) : other.note == null);
    }
    

    @Override
    public String toString() {
        return "AdministrationWorklogResponse{note=" + note + '}';
    }
}
