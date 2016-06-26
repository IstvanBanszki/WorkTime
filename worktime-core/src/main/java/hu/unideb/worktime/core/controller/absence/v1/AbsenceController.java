
package hu.unideb.worktime.core.controller.absence.v1;

import hu.unideb.worktime.api.model.absence.GetAbsenceResponse;
import hu.unideb.worktime.api.model.absence.SaveAbsenceRequest;
import hu.unideb.worktime.jdbc.absence.SqlCallAbsence;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    /*
    --------------------
    Example JSON content
    --------------------
    {
        "begin": "2016-05-11T08:00:00.000",
        "end": "2016-05-11T16:00:00.000",
        "workerId": 1,
        "absenceType": 4
    }
     */
    @Async
    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public @ResponseBody Integer saveWorklog(@RequestBody SaveAbsenceRequest request) {
        Integer result = null;

        this.logger.info("Calling /rest/absence/v1/save webservice with the following key: {}", request);
        result = this.sqlCallAbsence.saveAbsence(request);
        this.logger.info("Result of /rest/absence/v1/save webservice: {}", result);

        return result;
    }
    
    @Async
    @RequestMapping(value = "/get/{workerId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<GetAbsenceResponse> getWorklog(@PathVariable Integer workerId) {
        List<GetAbsenceResponse> result = null;

        this.logger.info("Calling /rest/absence/v1/get webservice with the following key: {}", workerId);
        result = this.sqlCallAbsence.getAbsence(workerId);
        this.logger.info("Result of /rest/absence/v1/get webservice: {}", result);

        return result;
    }
}
