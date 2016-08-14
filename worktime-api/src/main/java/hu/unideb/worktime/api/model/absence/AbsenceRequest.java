package hu.unideb.worktime.api.model.absence;

import hu.unideb.worktime.api.model.AbsenceType;
import java.io.Serializable;
import java.util.Date;

public class AbsenceRequest implements Serializable{
    
    private final Date begin;
    private final Date end;
    private final AbsenceType absenceType;

    public AbsenceRequest() {
        this.begin = null;
        this.end = null;
        this.absenceType = AbsenceType.NOT_SET;
    }

    public AbsenceRequest(Date begin, Date end, int workerId, AbsenceType absenceType) {
        this.begin = begin;
        this.end = end;
        this.absenceType = absenceType;
    }

    public Date getBegin() {
        return this.begin;
    }
    
    public Date getEnd() {
        return this.end;
    }

    public AbsenceType getAbsenceType() {
        return this.absenceType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.begin != null ? this.begin.hashCode() : 0);
        hash = 29 * hash + (this.end != null ? this.end.hashCode() : 0);
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
        final AbsenceRequest other = (AbsenceRequest) obj;
        return (this.begin != null ? this.begin.equals(other.begin) : other.begin == null) &&
               (this.end != null ? this.end.equals(other.end) : other.end == null) &&
               (this.absenceType != null ? this.absenceType.equals(other.absenceType) : other.absenceType == null);
    }

    @Override
    public String toString() {
        return "SaveAbsenceRequest{begin=" + this.begin + ", end=" + this.end +
                ", absenceType=" + this.absenceType + '}';
    }
}
