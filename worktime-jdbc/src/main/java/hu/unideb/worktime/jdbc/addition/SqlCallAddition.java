package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Department;
import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.jdbc.worklog.SqlCallWorklog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAddition {
    
    @Autowired private SpGetAllDepartments spGetAllDepartments;
    @Autowired private SpGetAllOffices spGetAllOffices;
    @Autowired private SpEditOffice spEditOffice;
    @Autowired private SpEditDepartment spEditDepartment;
    private Logger logger = LoggerFactory.getLogger(SqlCallWorklog.class);
    
    
    public List<Office> getOffices() {
        List<Office> result = null;
        this.logger.info("Call get_all_offices SP with given parameters.");
        try {
            result = this.spGetAllOffices.execute();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such office in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_offices SP call: {}", ex);
        }
        this.logger.info("Result of get_all_offices SP: {}", result);
        return result;
    }

    public List<Department> getDepartments() {
        List<Department> result = null;
        this.logger.info("Call get_all_deparments SP with given parameters.");
        try {
            result = this.spGetAllDepartments.execute();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such department in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_deparments SP call: {}", ex);
        }
        this.logger.info("Result of get_all_deparments SP: {}", result);
        return result;
    }
    
    public Map<Office, Department> getOfficesWithDepartment() {
        Map<Office, Department> result = new HashMap();
        List<Office> offices = getOffices();
        List<Department> departments = getDepartments();

        offices.stream().forEach((Office office) -> {
            departments.stream().filter((department) -> (office.getId() == department.getOfficeId())).forEach((department) -> {
                result.put(office, department);
            });
        });
        return result;
    }
    
    public Integer editOffice(Integer id, Office values) {
        Integer result = null;
        this.logger.info("Call edit_office SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditOffice.execute(id, values);
            if (result == null) {
                this.logger.debug("There is an error while edit the office in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_office SP call: {}", ex);
        }
        this.logger.info("Result of edit_office SP: {}", result);
        return result;
    }
    
    public Integer editDepartment(Integer id, Department values) {
        Integer result = null;
        this.logger.info("Call edit_department SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditDepartment.execute(id, values);
            if (result == null) {
                this.logger.debug("There is an error while edit the department in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_office SP call: {}", ex);
        }
        this.logger.info("Result of edit_department SP: {}", result);
        return result;
    }

}
