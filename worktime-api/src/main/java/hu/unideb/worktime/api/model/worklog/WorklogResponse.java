package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorklogResponse implements Serializable {

    private final int id;
    private final LocalDateTime beginDate;
    private final int workHour;

    public WorklogResponse(int id, LocalDateTime beginDate, int workHour) {
        this.id = id;
        this.beginDate = beginDate;
        this.workHour = workHour;
    }

    public int getId() {
        return this.id;
    }

    public LocalDateTime getBeginDate() {
        return this.beginDate;
    }

    public int getWorkHour() {
        return this.workHour;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + (this.beginDate != null ? this.beginDate.hashCode() : 0);
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
        return (this.beginDate != null ? this.beginDate.equals(other.beginDate) : other.beginDate == null) &&
               (this.workHour == other.workHour) &&
               (this.id == other.id);
    }

    @Override
    public String toString() {
        return "WorklogResponse{id=" + this.id + ", beginDate=" + this.beginDate + ", workHour=" + this.workHour + '}';
    }

}
