package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;

public class AdministrationWorklogRequest implements Serializable{

    private final String dateFilter;
    private final Boolean showDailyWorkhours; 
    
    public AdministrationWorklogRequest() {
        this.dateFilter = "";
        this.showDailyWorkhours = false;
    }

    public AdministrationWorklogRequest(String dateFilter, Boolean showDailyWorkhours) {
        this.dateFilter = dateFilter;
        this.showDailyWorkhours = showDailyWorkhours;
    }

    public String getDateFilter() {
        return this.dateFilter;
    }

    public Boolean isShowDailyWorkhours() {
        return showDailyWorkhours;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.dateFilter != null ? this.dateFilter.hashCode() : 0);
        hash = 29 * hash + (this.showDailyWorkhours != null ? this.showDailyWorkhours.hashCode() : 0);
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
        final AdministrationWorklogRequest other = (AdministrationWorklogRequest) obj;
        return (this.dateFilter != null ? this.dateFilter.equals(other.dateFilter) : other.dateFilter == null) &&
               (this.showDailyWorkhours != null ? this.showDailyWorkhours.equals(other.showDailyWorkhours) : other.showDailyWorkhours == null);
    }

    @Override
    public String toString() {
        return "AdministrationWorklogRequest{dateFilter=" + dateFilter + ", showDailyWorkhours=" + showDailyWorkhours + '}';
    }

}
