package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;
import java.util.Date;

public class WorklogRequest implements Serializable{
    
    private final Date beginDate;
    private final int workHour;

    public WorklogRequest() {
        this.beginDate = null;
        this.workHour = 0;
    }

    public WorklogRequest(Date beginDate, int workHour, int workerId) {
        this.beginDate = beginDate;
        this.workHour = workHour;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public int getWorkHour() {
        return this.workHour;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.beginDate != null ? this.beginDate.hashCode() : 0);
        hash = 29 * hash + this.workHour;
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
        final WorklogRequest other = (WorklogRequest) obj;
        return this.workHour != other.workHour &&
              (this.beginDate != null ? this.beginDate.equals(other.beginDate) : other.beginDate == null);
    }

    @Override
    public String toString() {
        return "SaveWorklogRequest{beginDate=" + this.beginDate + ", workHour=" + this.workHour + '}';
    }
}
