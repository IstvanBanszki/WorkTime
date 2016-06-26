package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SaveWorklogRequest implements Serializable{
    
    private final LocalDateTime begin;
    private final int workHour;
    private final int workerId;

    public SaveWorklogRequest() {
        this.begin = null;
        this.workHour = 0;
        this.workerId = 0;
    }

    public SaveWorklogRequest(LocalDateTime begin, int workHour, int workerId) {
        this.begin = begin;
        this.workHour = workHour;
        this.workerId = workerId;
    }

    public LocalDateTime getBegin() {
        return this.begin;
    }
    
    public int getWorkHour() {
        return this.workHour;
    }

    public int getWorkerId() {
        return this.workerId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.begin != null ? this.begin.hashCode() : 0);
        hash = 29 * hash + this.workHour;
        hash = 29 * hash + this.workerId;
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
        return this.workerId != other.workerId &&
               this.workHour != other.workHour &&
              (this.begin != null ? this.begin.equals(other.begin) : other.begin == null);
    }

    @Override
    public String toString() {
        return "SaveWorklogRequest{begin=" + this.begin + ", workHour=" + this.workHour +
                ", workerId=" + this.workerId + '}';
    }
}
