package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class AdministrationAbsenceRequest implements Serializable{

    private final String dateFilter; 
    private final Boolean notApprove;
    
    public AdministrationAbsenceRequest() {
        this.dateFilter = "";
        this.notApprove = Boolean.FALSE;
    }

    public AdministrationAbsenceRequest(String dateFilter, Boolean notAccepted) {
        this.dateFilter = dateFilter;
        this.notApprove = notAccepted;
    }

    public String getDateFilter() {
        return this.dateFilter;
    }

    public Boolean isNotApprove() {
        return notApprove;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.dateFilter != null ? this.dateFilter.hashCode() : 0);
        hash = 29 * hash + (this.notApprove != null ? this.notApprove.hashCode() : 0);
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
               (this.notApprove != null ? this.notApprove.equals(other.notApprove) : other.notApprove == null);
    }

    @Override
    public String toString() {
        return "AdministrationAbsenceRequest{dateFilter=" + dateFilter + ", notAccepted=" + notApprove + '}';
    }
    
}
