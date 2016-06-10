package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SaveWorklogRequest implements Serializable{
    
    private final String description;
    private final LocalDateTime begin;
    private final LocalDateTime end;
    private final int workerId;

    public SaveWorklogRequest() {
        this.description = "";
        this.begin = null;
        this.end = null;
        this.workerId = 0;
    }

    public SaveWorklogRequest(String description, LocalDateTime begin, LocalDateTime end, int workerId) {
        this.description = description;
        this.begin = begin;
        this.end = end;
        this.workerId = workerId;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getBegin() {
        return this.begin;
    }
    
    public LocalDateTime getEnd() {
        return this.end;
    }

    public int getWorkerId() {
        return this.workerId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 29 * hash + (this.begin != null ? this.begin.hashCode() : 0);
        hash = 29 * hash + (this.end != null ? this.end.hashCode() : 0);
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
              (this.description != null ? this.description.equals(other.description) : other.description == null) &&
              (this.begin != null ? this.begin.equals(other.begin) : other.begin == null) &&
              (this.end != null ? this.end.equals(other.end) : other.end == null);
    }

    @Override
    public String toString() {
        return "SaveWorklogRequest{" + "description=" + this.description + ", begin=" + this.begin + ", end=" + this.end + ", workerId=" + this.workerId + '}';
    }
}