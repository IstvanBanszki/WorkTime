package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.api.model.SaveResult;
import java.util.List;

public interface ISqlCallOfficeAddition {
    
    List<Office> getOffices();
    Integer editOffice(Integer id, Office values);
    SaveResult saveOffice(Office values);
    
}
