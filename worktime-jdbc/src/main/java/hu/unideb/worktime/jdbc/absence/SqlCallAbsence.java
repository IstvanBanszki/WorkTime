package hu.unideb.worktime.jdbc.absence;

import hu.unideb.worktime.api.model.absence.AbsenceDataResponse;
import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import hu.unideb.worktime.api.model.absence.AbsenceRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlCallAbsence {

    @Autowired private SpGetAbsence spGetAbsence;
    @Autowired private SpSaveAbsence spSaveAbsence;
    @Autowired private SpGetAbsenceData spGetAbsenceData;
    @Autowired private SpDeleteAbsence spDeleteAbsence;
    @Autowired private SpEditAbsence spEditAbsence;
    private Logger logger;

    public SqlCallAbsence() {
        this.logger = LoggerFactory.getLogger(SqlCallAbsence.class);
    }

    public Integer saveAbsence(Integer workerId, AbsenceRequest values) {
        Integer result = null;
        this.logger.info("Call save_absence SP with given parameters: Key - {}, Values - {}", workerId, values);
        try {
            result = this.spSaveAbsence.execute(workerId, values);
            if (result == null) {
                this.logger.debug("There is an error while saving the absence in database! Key: {}, Values - {}", workerId, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_absence SP call: {}", ex);
        }
        this.logger.info("Result of save_absence SP: {}", result);
        return result;
    }

    public List<AbsenceResponse> getAbsence(Integer key, String request) {
        List<AbsenceResponse> result = null;
        this.logger.info("Call get_all_absence_by_worker SP with given parameters - Key {}, dateFilter: {}", key, request);
        try {
            result = this.spGetAbsence.execute(key, request);
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such absences in database! Key: {}, dateFilter: {}", key, request);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_all_absence_by_worker SP call: {}", ex);
        }
        this.logger.info("Result of get_all_absence_by_worker SP: {}", result);
        return result;
    }

    public List<AbsenceDataResponse> getAbsenceData(Integer key) {
        List<AbsenceDataResponse> result = null;
        this.logger.info("Call get_absence_data SP with given parameters: {}", key);
        try {
            result = this.spGetAbsenceData.execute(key);
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no such absence in database! Key: {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_absence_data SP call: {}", ex);
        }
        this.logger.info("Result of get_absence_data SP: {}", result);
        return result;
    }
    
    public Integer deleteAbsence(Integer key) {
        Integer result = null;
        this.logger.info("Call delete_absence SP with given parameters: Key - {}", key);
        try {
            result = this.spDeleteAbsence.execute(key);
            if (result == null) {
                this.logger.debug("There is an error while delete the absence in database! Key - {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during delete_absence SP call: {}", ex);
        }
        this.logger.info("Result of delete_absence SP: {}", result);
        return result;
    }
    
    public Integer editAbsence(Integer id, AbsenceRequest values) {
        Integer result = null;
        this.logger.info("Call edit_absence SP with given parameters: Key - {}, values - {}", id, values);
        try {
            result = this.spEditAbsence.execute(id, values);
            if (result == null) {
                this.logger.debug("There is an error while edit the absence in database! Key - {}, values - {}", id, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during edit_absence SP call: {}", ex);
        }
        this.logger.info("Result of edit_absence SP: {}", result);
        return result;
    }
}
