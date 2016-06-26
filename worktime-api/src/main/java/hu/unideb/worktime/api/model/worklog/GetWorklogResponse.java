package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GetWorklogResponse implements Serializable {

    private final LocalDateTime begin;
    private final int workHour;

    public GetWorklogResponse(LocalDateTime begin, int workHour) {
        this.begin = begin;
        this.workHour = workHour;
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
        final GetWorklogResponse other = (GetWorklogResponse) obj;
        return (this.begin != null ? this.begin.equals(other.begin) : other.begin == null) &&
               (this.workHour == other.workHour);
    }

    @Override
    public String toString() {
        return "GetWorklogResponse{begin=" + this.begin + ", workHour=" + this.workHour + '}';
    }
}
