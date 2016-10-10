package hu.unideb.worktime.api.model.administration;

import java.io.Serializable;
    
public class Note implements Serializable {
    
    private String note;

    public Note() {
}

    public String getNote() {
        return this.note;
    }
    
}
