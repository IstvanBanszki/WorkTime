package hu.unideb.worktime.api.model;

import java.io.Serializable;

public class SaveResult implements Serializable {
    
    private final int newId;
    private final int status;

    public SaveResult(int newId, int status) {
        this.newId = newId;
        this.status = status;
    }

    public int getNewId() {
        return newId;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.newId;
        hash = 97 * hash + this.status;
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
        final SaveResult other = (SaveResult) obj;
        return (this.newId == other.newId) &&
               (this.status == other.status);
    }
    
    @Override
    public String toString() {
        return "SaveResult{newId=" + newId + ", status=" + status + '}';
    }
    
}
