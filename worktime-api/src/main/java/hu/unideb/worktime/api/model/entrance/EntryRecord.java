package hu.unideb.worktime.api.model.entrance;

import java.time.LocalDateTime;

public class EntryRecord {
    
    private final int workerId;
    private final LocalDateTime logTimeStamp;
    private final int inOut;

    public EntryRecord(int workerId, LocalDateTime logTimeStamp, int inOut) {
        this.workerId = workerId;
        this.logTimeStamp = logTimeStamp;
        this.inOut = inOut;
    }

    public int getWorkerId() {
        return this.workerId;
    }

    public LocalDateTime getLogTimeStamp() {
        return this.logTimeStamp;
    }

    public int getInOut() {
        return this.inOut;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.workerId;
        hash = 71 * hash + (this.logTimeStamp != null ? this.logTimeStamp.hashCode() : 0);
        hash = 71 * hash + this.inOut;
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
        final EntryRecord other = (EntryRecord) obj;
        return (this.workerId == other.workerId) &&
               (this.inOut == other.inOut) &&
               (this.logTimeStamp != null ? this.logTimeStamp.equals(other.logTimeStamp) : other.logTimeStamp == null );
    }

    @Override
    public String toString() {
        return "EntranceRecord{workerId=" + this.workerId + ", logTimeStamp=" + this.logTimeStamp + ", inOut=" + this.inOut + '}';
    }
    
}
