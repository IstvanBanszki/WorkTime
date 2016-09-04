package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class AdministrationRequest implements Serializable{

    private final String dateFilter;
    
    public AdministrationRequest() {
        this.dateFilter = "";
    }

    public AdministrationRequest(String dateFilter, int workerId) {
        this.dateFilter = dateFilter;
    }

    public String getDateFilter() {
        return dateFilter;
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
        final AdministrationRequest other = (AdministrationRequest) obj;
        return (this.dateFilter != null ? this.dateFilter.equals(other.dateFilter) : other.dateFilter == null);
    }

    @Override
    public String toString() {
        return "AdministrationRequest{dateFilter=" + dateFilter + '}';
    }
}
