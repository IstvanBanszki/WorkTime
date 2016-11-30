package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;

public class MontlyStatRequest implements Serializable {
    
    private final int workerId;
    private final int month;
    private final int year;
    private final int exportType;

    public MontlyStatRequest(int workerId, int year, int month, int exportType) {
        this.workerId = workerId;
        this.month = month;
        this.year = year;
        this.exportType = exportType;
    }
    
    public MontlyStatRequest() {
        this.workerId = 0;
        this.month = 0;
        this.year = 0;
        this.exportType = 0;
    }

    public int getWorkerId() {
        return workerId;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getExportType() {
        return exportType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.workerId;
        hash = 11 * hash + this.month;
        hash = 11 * hash + this.year;
        hash = 11 * hash + this.exportType;
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
        final MontlyStatRequest other = (MontlyStatRequest) obj;
        return (this.month == other.month) &&
               (this.year == other.year) &&
               (this.workerId == other.workerId) &&
               (this.exportType == other.exportType);
    }

    @Override
    public String toString() {
        return "MontlyStatRequest{workerId=" + workerId + ", month=" + month + 
                ", year=" + year + ", exportType=" + exportType + '}';
    }
    
}
