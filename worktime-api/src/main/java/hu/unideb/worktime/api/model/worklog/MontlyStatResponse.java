package hu.unideb.worktime.api.model.worklog;

import java.io.Serializable;

public class MontlyStatResponse implements Serializable {
    
    private final int sumOfWorkHour;
    private final int countOfWorkHour;
    private final int avarageWorkHour;

    public MontlyStatResponse(int sumOfWorkHour, int countOfWorkHour, int avarageWorkHour) {
        this.sumOfWorkHour = sumOfWorkHour;
        this.countOfWorkHour = countOfWorkHour;
        this.avarageWorkHour = avarageWorkHour;
    }

    public int getSumOfWorkHour() {
        return sumOfWorkHour;
    }

    public int getCountOfWorkHour() {
        return countOfWorkHour;
    }

    public int getAvarageWorkHour() {
        return avarageWorkHour;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.sumOfWorkHour;
        hash = 11 * hash + this.countOfWorkHour;
        hash = 11 * hash + this.avarageWorkHour;
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
        final MontlyStatResponse other = (MontlyStatResponse) obj;
        return (this.sumOfWorkHour == other.sumOfWorkHour) &&
               (this.countOfWorkHour == other.countOfWorkHour) &&
               (this.avarageWorkHour == other.avarageWorkHour);
    }

    @Override
    public String toString() {
        return "MontlyStatResponse{sumOfWorkHour=" + sumOfWorkHour + ", countOfWorkHour=" 
                + countOfWorkHour + ", avarageWorkHour=" + avarageWorkHour + '}';
    }

}
