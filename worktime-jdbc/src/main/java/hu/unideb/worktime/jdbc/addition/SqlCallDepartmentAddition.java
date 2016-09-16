package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Department;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallDepartmentAddition {
    
    @Autowired private SpGetAllDepartments spGetAllDepartments;
    @Autowired private SpEditDepartment spEditDepartment;
    @Autowired private SpSaveDepartment spSaveDepartment;
    private Logger logger = LoggerFactory.getLogger(SqlCallDepartmentAddition.class);
    
    public List<Department> getDepartments() {
        List<Department> result = null;
        this.logger.info("Call get_all_deparments SP with given parameters.");
        try {
            result = this.spGetAllDepartments.getDepartments();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such department in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_deparments SP call: {}", ex);
        }
        this.logger.info("Result of get_all_deparments SP: {}", result);
        return result;
    }
    
    public Integer editDepartment(Integer id, Department values) {
        Integer result = null;
        this.logger.info("Call edit_department SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditDepartment.editDepartment(id, values);
            if (result == null) {
                this.logger.debug("There is an error while edit the department in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_office SP call: {}", ex);
        }
        this.logger.info("Result of edit_department SP: {}", result);
        return result;
    }
    
    public Integer saveDepartment(Department values) {
        Integer result = null;
        this.logger.info("Call save_department SP with given parameters: values - {}", values);
        try {
            result = this.spSaveDepartment.saveDepartment(values);
            if (result == null) {
                this.logger.debug("There is an error while save the department in database! values - {}", values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_department SP call: {}", ex);
        }
        this.logger.info("Result of save_department SP: {}", result);
        return result;
    }

}
