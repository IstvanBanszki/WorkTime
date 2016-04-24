package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class WorkLog {

    private final int id;
    private final String description;
    private final WorkStatus workStatus;
    private final AbsenceType absenceType;
    private final LocalDateTime begin;
    private final LocalDateTime end;
    private final LocalDateTime dateOfRegistration;
    private final LocalDateTime dateOfModification;

    private WorkLog(int id, String description, WorkStatus workStatus, AbsenceType absenceType, LocalDateTime begin, LocalDateTime end, LocalDateTime dateOfRegistration, LocalDateTime dateOfModification) {
        this.id = id;
        this.description = description;
        this.workStatus = workStatus;
        this.absenceType = absenceType;
        this.begin = begin;
        this.end = end;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfModification = dateOfModification;
    }

    private static class WorkLogBuilder {

        private int id;
        private String description;
        private WorkStatus workStatus;
        private AbsenceType absenceType;
        private LocalDateTime begin;
        private LocalDateTime end;
        private LocalDateTime dateOfRegistration;
        private LocalDateTime dateOfModification;

        public WorkLogBuilder() {
            this.id = 0;
            this.description = "";
            this.workStatus = WorkStatus.NOT_SET;
            this.absenceType = null;
            this.begin = null;
            this.end = null;
            this.dateOfRegistration = null;
            this.dateOfModification = null;
        }

        public WorkLogBuilder id(int id) {
            this.id = id;
            return this;
        }

        public WorkLogBuilder description(String description) {
            this.description = description;
            return this;
        }

        public WorkLogBuilder workStatus(WorkStatus workStatus) {
            this.workStatus = workStatus;
            return this;
        }

        public WorkLogBuilder absenceType(AbsenceType absenceType) {
            this.absenceType = absenceType;
            return this;
        }

        public WorkLogBuilder begin(LocalDateTime begin) {
            this.begin = begin;
            return this;
        }

        public WorkLogBuilder end(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public WorkLogBuilder dateOfRegistration(LocalDateTime dateOfRegistration) {
            this.dateOfRegistration = dateOfRegistration;
            return this;
        }

        public WorkLogBuilder dateOfModification(LocalDateTime dateOfModification) {
            this.dateOfModification = dateOfModification;
            return this;
        }

        public WorkLog build() {
            return new WorkLog(this.id, this.description, this.workStatus, this.absenceType,
                    this.begin, this.end, this.dateOfRegistration, this.dateOfModification);
        }
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public LocalDateTime getDateOfModification() {
        return dateOfModification;
    }

    @Override
    public String toString() {
        return "WorkLog{" + "id=" + id + ", description=" + description + ", workStatus="
                + workStatus + ", absenceType=" + absenceType + ", begin=" + begin
                + ", end=" + end + ", dateOfRegistration=" + dateOfRegistration
                + ", dateOfModification=" + dateOfModification + '}';
    }
}
