package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.jdbc.addition.storedprocedure.SpSaveOffice;
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpGetAllOffices;
import hu.unideb.worktime.jdbc.addition.storedprocedure.SpEditOffice;
import hu.unideb.worktime.api.model.Office;
import hu.unideb.worktime.api.model.SaveResult;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallOfficeAddition implements ISqlCallOfficeAddition {
    
    @Autowired private SpGetAllOffices spGetAllOffices;
    @Autowired private SpEditOffice spEditOffice;
    @Autowired private SpSaveOffice spSaveOffice;
    private Logger logger = LoggerFactory.getLogger(SqlCallOfficeAddition.class);
    
    @Override
    public List<Office> getOffices() {
        List<Office> result = null;
        this.logger.info("Call get_all_offices SP with given parameters.");
        try {
            result = this.spGetAllOffices.getOffices();
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such office in database!");
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_offices SP call: {}", ex);
        }
        this.logger.info("Result of get_all_offices SP: {}", result);
        return result;
    }
    
    @Override
    public Integer editOffice(Integer officeId, Office value) {
        Integer result = null;
        this.logger.info("Call edit_office SP with given parameters: Key - {}, values - {}", officeId, value);
        try {
            result = this.spEditOffice.editOffice(officeId, value);
            if (result == null) {
                this.logger.debug("There is an error while edit the office in database! Key - {}, values - {}", officeId, value);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_office SP call: {}", ex);
        }
        this.logger.info("Result of edit_office SP: {}", result);
        return result;
    }
    
    @Override
    public SaveResult saveOffice(Office values) {
        SaveResult result = null;
        this.logger.info("Call save_office SP with given parameters: Key - {}, values - {}", values);
        try {
            result = this.spSaveOffice.saveOffice(values);
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
