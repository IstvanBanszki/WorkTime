package hu.unideb.worktime.api.model.absence;

import hu.unideb.worktime.api.model.AbsenceType;
import java.io.Serializable;
import java.util.Date;

public class SaveAbsenceRequest implements Serializable{
    
    private final String description;
    private final Date begin;
    private final Date end;
    private final int workerId;
    private final AbsenceType absenceType;

    public SaveAbsenceRequest() {
        this.description = "";
        this.begin = null;
        this.end = null;
        this.workerId = 0;
        this.absenceType = AbsenceType.NOT_SET;
    }

    public SaveAbsenceRequest(String description, Date begin, Date end, int workerId, AbsenceType absenceType) {
        this.description = description;
        this.begin = begin;
        this.end = end;
        this.workerId = workerId;
        this.absenceType = absenceType;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getBegin() {
        return this.begin;
    }
    
    public Date getEnd() {
        return this.end;
    }

    public int getWorkerId() {
        return this.workerId;
    }

    public AbsenceType getAbsenceType() {
        return this.absenceType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 29 * hash + (this.begin != null ? this.begin.hashCode() : 0);
        hash = 29 * hash + (this.end != null ? this.end.hashCode() : 0);
        hash = 29 * hash + this.workerId;
        hash = 29 * hash + (this.absenceType != null ? this.absenceType.hashCode() : 0);
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
        final SaveAbsenceRequest other = (SaveAbsenceRequest) obj;
        return this.workerId != other.workerId &&
              (this.description != null ? this.description.equals(other.description) : other.description == null) &&
              (this.begin != null ? this.begin.equals(other.begin) : other.begin == null) &&
              (this.end != null ? this.end.equals(other.end) : other.end == null) &&
              (this.absenceType != null ? this.absenceType.equals(other.absenceType) : other.absenceType == null);
    }

    @Override
    public String toString() {
        return "SaveAbsenceRequest{" + "description=" + this.description + ", begin=" 
                + this.begin + ", end=" + this.end + ", workerId=" + this.workerId
                + ", absenceType=" + this.absenceType + '}';
    }
}
