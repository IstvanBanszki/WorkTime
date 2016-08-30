package hu.unideb.worktime.api.model.absence;

import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.AbsenceType;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AbsenceResponse implements Serializable {

    private final int id;
    private final LocalDateTime begin;
    private final LocalDateTime end;
    private final Status status;
    private final AbsenceType absenceType;

    public AbsenceResponse(int id, LocalDateTime begin, LocalDateTime end, Status status, AbsenceType absenceType) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.status = status;
        this.absenceType = absenceType;
    }

    public static class AbsenceResponseBuilder {

        private int id;
        private LocalDateTime begin;
        private LocalDateTime end;
        private Status status;
        private AbsenceType absenceType;

        public AbsenceResponseBuilder() {
            this.begin = null;
            this.end = null;
            this.status = Status.NOT_SET;
        }

        public AbsenceResponseBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AbsenceResponseBuilder setBegin(LocalDateTime begin) {
            this.begin = begin;
            return this;
        }

        public AbsenceResponseBuilder setEnd(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public AbsenceResponseBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public AbsenceResponseBuilder setAbsenceType(AbsenceType absenceType) {
            this.absenceType = absenceType;
            return this;
        }

        public AbsenceResponse build() {
            return new AbsenceResponse(this.id, this.begin, this.end, this.status, this.absenceType);
        }
    }    

    public int getId() {
        return id;
    }

    public LocalDateTime getBegin() {
        return this.begin;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public Status getStatus() {
        return this.status;
    }

    public AbsenceType getAbsenceType() {
        return this.absenceType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + (this.begin != null ? this.begin.hashCode() : 0);
        hash = 67 * hash + (this.end != null ? this.end.hashCode() : 0);
        hash = 67 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 67 * hash + (this.absenceType != null ? this.absenceType.hashCode() : 0);
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
        final AbsenceResponse other = (AbsenceResponse) obj;
        return (this.begin != null ? this.begin.equals(other.begin) : other.begin == null) &&
               (this.end != null ? this.end.equals(other.end) : other.end == null) &&
               (this.status == other.status) &&
               (this.absenceType == other.absenceType) &&
               (this.id == other.id);
    }

    @Override
    public String toString() {
        return "AbsenceResponse{id=" + id + ", begin=" + begin + ", end=" + end +
                ", status=" + status + ", absenceType=" + absenceType + '}';
    }
}
