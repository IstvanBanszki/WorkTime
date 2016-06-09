
package hu.unideb.worktime.core.controller.absence.v1;

import hu.unideb.worktime.api.model.absence.GetAbsenceResponse;
import hu.unideb.worktime.jdbc.absence.SqlCallAbsence;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/absence/v1")
public class AbsenceController {

    @Autowired
    private SqlCallAbsence sqlCallAbsence;

    private Logger logger;

    public AbsenceController() {
        this.logger = LoggerFactory.getLogger(AbsenceController.class);
    }
    

    @Async
    @RequestMapping(value = "/getabsence/{workerId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<GetAbsenceResponse> getWorklog(@PathVariable Integer workerId) {
        List<GetAbsenceResponse> result = null;

        this.logger.info("Calling /rest/absence/v1/getabsence webservice with the following key: {}", workerId);
        result = this.sqlCallAbsence.getAbsence(workerId);
        this.logger.info("Result of /rest/absence/v1/getabsence webservice: {}", result);

        return result;
    }
}
