package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;
import java.util.Date;

public class SaveWorklogRequest implements Serializable{
    
    private final Date begin;
    private final int workHour;

    public SaveWorklogRequest() {
        this.begin = null;
        this.workHour = 0;
    }

    public SaveWorklogRequest(Date begin, int workHour, int workerId) {
        this.begin = begin;
        this.workHour = workHour;
    }

    public Date getBegin() {
        return this.begin;
    }
    
    public int getWorkHour() {
        return this.workHour;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.begin != null ? this.begin.hashCode() : 0);
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
        final SaveWorklogRequest other = (SaveWorklogRequest) obj;
        return this.workHour != other.workHour &&
              (this.begin != null ? this.begin.equals(other.begin) : other.begin == null);
    }

    @Override
    public String toString() {
        return "SaveWorklogRequest{begin=" + this.begin + ", workHour=" + this.workHour + '}';
    }
}
