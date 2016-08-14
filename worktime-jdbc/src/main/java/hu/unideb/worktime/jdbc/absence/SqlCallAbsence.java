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

    @Autowired
    private SpGetAbsence spGetAbsence;
    @Autowired
    private SpSaveAbsence spSaveAbsence;
    @Autowired
    private SpGetAbsenceData spGetAbsenceData;
    private Logger logger;

    public SqlCallAbsence() {
        this(LoggerFactory.getLogger(SqlCallAbsence.class));
    }

    public SqlCallAbsence(Logger logger) {
        this.logger = logger;
    }

    public Integer saveAbsence(Integer workerId, AbsenceRequest values) {
        Integer result = null;
        this.logger.info("Call save_absence SP with given parameters: Key - {}, Values - {}", workerId, values);
        try {
            result = this.spSaveAbsence.execute(workerId, values);
            if (result == null) {
                this.logger.debug("There is an erro in saving the worklog data in database! Key: {}, Values - {}", workerId, values);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during save_absence SP call: {}", ex);
        }
        this.logger.info("Result of save_absence SP: {}", result);
        return result;
    }

    public List<AbsenceResponse> getAbsence(Integer key) {
        List<AbsenceResponse> result = null;
        this.logger.info("Call get_all_absence_by_worker SP with given parameters: {}", key);
        try {
            result = this.spGetAbsence.execute(key);
            if (result == null || result.isEmpty()) {
                this.logger.debug("There is no suche worklog data in database! Key: {}", key);
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
                this.logger.debug("There is no suche worklog data in database! Key: {}", key);
            }
        } catch (Exception ex) {
            this.logger.error("There is an exception during get_absence_data SP call: {}", ex);
        }
        this.logger.info("Result of get_absence_data SP: {}", result);
        return result;
    }
}
