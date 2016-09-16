package hu.unideb.worktime.api.model.absence;

import hu.unideb.worktime.api.model.Status;
import hu.unideb.worktime.api.model.AbsenceType;
import java.io.Serializable;
import java.time.LocalDate;

public class AbsenceResponse implements Serializable {

    private final int id;
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final Status status;
    private final AbsenceType absenceType;

    public AbsenceResponse(int id, LocalDate beginDate, LocalDate endDate, Status status, AbsenceType absenceType) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.absenceType = absenceType;
    }

    public static class Builder {

        private int id;
        private LocalDate beginDate;
        private LocalDate endDate;
        private Status status;
        private AbsenceType absenceType;

        public Builder() {
            this.beginDate = null;
            this.endDate = null;
            this.status = Status.NOT_SET;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setBeginDate(LocalDate begin) {
            this.beginDate = begin;
            return this;
        }

        public Builder setEndDate(LocalDate end) {
            this.endDate = end;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setAbsenceType(AbsenceType absenceType) {
            this.absenceType = absenceType;
            return this;
        }

        public AbsenceResponse build() {
            return new AbsenceResponse(this.id, this.beginDate, this.endDate, this.status, this.absenceType);
        }
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getBeginDate() {
        return this.beginDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
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
        hash = 67 * hash + (this.beginDate != null ? this.beginDate.hashCode() : 0);
        hash = 67 * hash + (this.endDate != null ? this.endDate.hashCode() : 0);
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
        return (this.beginDate != null ? this.beginDate.equals(other.beginDate) : other.beginDate == null) &&
               (this.endDate != null ? this.endDate.equals(other.endDate) : other.endDate == null) &&
               (this.status == other.status) &&
               (this.absenceType == other.absenceType) &&
               (this.id == other.id);
    }

    @Override
    public String toString() {
        return "AbsenceResponse{id=" + this.id + ", beginDate=" + this.beginDate + ", endDate=" + this.endDate +
                ", status=" + this.status + ", absenceType=" + this.absenceType + '}';
    }
}
