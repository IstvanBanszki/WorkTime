package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Department;
import hu.unideb.worktime.api.model.SaveResult;
import java.util.List;

public interface ISqlCallDepartmentAddition {
    
    List<Department> getDepartments();
    Integer editDepartment(Integer id, Department values);
    SaveResult saveDepartment(Department values);

}
