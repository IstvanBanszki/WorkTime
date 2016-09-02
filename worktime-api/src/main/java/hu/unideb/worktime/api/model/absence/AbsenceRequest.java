package hu.unideb.worktime.api.model.absence;

import hu.unideb.worktime.api.model.AbsenceType;
import java.io.Serializable;
import java.util.Date;

public class AbsenceRequest implements Serializable{
    
    private final Date beginDate;
    private final Date endDate;
    private final AbsenceType absenceType;

    public AbsenceRequest() {
        this.beginDate = null;
        this.endDate = null;
        this.absenceType = AbsenceType.NOT_SET;
    }

    public AbsenceRequest(Date beginDate, Date endDate, int workerId, AbsenceType absenceType) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.absenceType = absenceType;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public Date getEndDate() {
        return this.endDate;
    }

    public AbsenceType getAbsenceType() {
        return this.absenceType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.beginDate != null ? this.beginDate.hashCode() : 0);
        hash = 29 * hash + (this.endDate != null ? this.endDate.hashCode() : 0);
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
        return (this.beginDate != null ? this.beginDate.equals(other.beginDate) : other.beginDate == null) &&
               (this.endDate != null ? this.endDate.equals(other.endDate) : other.endDate == null) &&
               (this.absenceType != null ? this.absenceType.equals(other.absenceType) : other.absenceType == null);
    }

    @Override
    public String toString() {
        return "SaveAbsenceRequest{beginDate=" + this.beginDate + ", endDate=" + this.endDate +
                ", absenceType=" + this.absenceType + '}';
    }
}
