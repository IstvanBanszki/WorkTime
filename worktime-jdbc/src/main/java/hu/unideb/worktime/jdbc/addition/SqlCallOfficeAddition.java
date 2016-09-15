package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.Office;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallOfficeAddition {
    
    @Autowired private SpGetAllOffices spGetAllOffices;
    @Autowired private SpEditOffice spEditOffice;
    @Autowired private SpSaveOffice spSaveOffice;
    private Logger logger = LoggerFactory.getLogger(SqlCallOfficeAddition.class);
    
    
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
    
    public Integer saveOffice(Office values) {
        Integer result = null;
        this.logger.info("Call save_office SP with given parameters: Key - {}, values - {}", values);
        try {
            result = this.spSaveOffice.execute(values);
            if (result == null) {
                this.logger.debug("There is an error while save the office in database! Key - {}, values - {}", values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_office SP call: {}", ex);
        }
        this.logger.info("Result of save_office SP: {}", result);
        return result;
    }

}
