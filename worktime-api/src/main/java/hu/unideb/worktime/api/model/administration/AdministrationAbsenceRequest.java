package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class AdministrationAbsenceRequest implements Serializable{

    private final String dateFilter; 
    
    public AdministrationAbsenceRequest() {
        this.dateFilter = "";
    }

    public AdministrationAbsenceRequest(String dateFilter) {
        this.dateFilter = dateFilter;
    }

    public String getDateFilter() {
        return this.dateFilter;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.dateFilter != null ? this.dateFilter.hashCode() : 0);
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
        return (this.dateFilter != null ? this.dateFilter.equals(other.dateFilter) : other.dateFilter == null);
    }

    @Override
    public String toString() {
        return "AdministrationAbsenceRequest{dateFilter=" + dateFilter + '}';
    }

}
