package hu.unideb.worktime.api.model.worklog;

import hu.unideb.worktime.api.model.Status;
import java.io.Serializable;
import java.time.LocalDateTime;

public class GetWorklogResponse implements Serializable {

    private final LocalDateTime begin;
    private final LocalDateTime end;
    private final Status status;

    public GetWorklogResponse(LocalDateTime begin, LocalDateTime end, Status status) {
        this.begin = begin;
        this.end = end;
        this.status = status;
    }

    public static class GetWorklogResponseBuilder {

        private LocalDateTime begin;
        private LocalDateTime end;
        private Status status;

        public GetWorklogResponseBuilder() {
            this.begin = null;
            this.end = null;
            this.status = Status.NOT_SET;
        }

        public GetWorklogResponseBuilder setBegin(LocalDateTime begin) {
            this.begin = begin;
            return this;
        }

        public GetWorklogResponseBuilder setEnd(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public GetWorklogResponseBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public GetWorklogResponse build() {
            return new GetWorklogResponse(this.begin, this.end, this.status);
        }
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.begin != null ? this.begin.hashCode() : 0);
        hash = 67 * hash + (this.end != null ? this.end.hashCode() : 0);
        hash = 67 * hash + (this.status != null ? this.status.hashCode() : 0);
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
               (this.end != null ? this.end.equals(other.end) : other.end == null) &&
               (this.status == other.status);
    }

    @Override
    public String toString() {
        return "GetWorklogResponse{begin=" + this.begin + ", end=" + this.end + 
                ", status=" + this.status + '}';
    }
}
