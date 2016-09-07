package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class AdministrationAbsenceRequest implements Serializable{

    private final String dateFilter; 
    private final Boolean notAccepted;
    
    public AdministrationAbsenceRequest() {
        this.dateFilter = "";
        this.notAccepted = Boolean.FALSE;
    }

    public AdministrationAbsenceRequest(String dateFilter, Boolean notAccepted) {
        this.dateFilter = dateFilter;
        this.notAccepted = notAccepted;
    }

    public String getDateFilter() {
        return this.dateFilter;
    }

    public Boolean isNotAccepted() {
        return notAccepted;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.dateFilter != null ? this.dateFilter.hashCode() : 0);
        hash = 29 * hash + (this.notAccepted != null ? this.notAccepted.hashCode() : 0);
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
        final AdministrationAbsenceRequest other = (AdministrationAbsenceRequest) obj;
        return (this.dateFilter != null ? this.dateFilter.equals(other.dateFilter) : other.dateFilter == null) &&
               (this.notAccepted != null ? this.notAccepted.equals(other.notAccepted) : other.notAccepted == null);
    }

    @Override
    public String toString() {
        return "AdministrationAbsenceRequest{dateFilter=" + dateFilter + ", notAccepted=" + notAccepted + '}';
    }
    
}
