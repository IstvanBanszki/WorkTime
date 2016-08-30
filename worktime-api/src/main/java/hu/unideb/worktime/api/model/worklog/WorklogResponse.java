package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorklogResponse implements Serializable {

    private final int id;
    private final LocalDateTime begin;
    private final int workHour;

    public WorklogResponse(int id, LocalDateTime begin, int workHour) {
        this.id = id;
        this.begin = begin;
        this.workHour = workHour;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getBegin() {
        return this.begin;
    }

    public int getWorkHour() {
        return this.workHour;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + (this.begin != null ? this.begin.hashCode() : 0);
        hash = 67 * hash + this.workHour;
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
        final WorklogResponse other = (WorklogResponse) obj;
        return (this.begin != null ? this.begin.equals(other.begin) : other.begin == null) &&
               (this.workHour == other.workHour) &&
               (this.id == other.id);
    }

    @Override
    public String toString() {
        return "WorklogResponse{id=" + id + ", begin=" + begin + ", workHour=" + workHour + '}';
    }

}
