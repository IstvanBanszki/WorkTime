
package hu.unideb.worktime.core.controller.absence.v1;

import hu.unideb.worktime.api.model.absence.GetAbsenceResponse;
import hu.unideb.worktime.api.model.absence.SaveAbsenceRequest;
import hu.unideb.worktime.jdbc.absence.SqlCallAbsence;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/absence/v1", produces = "application/json")
public class AbsenceController {

    @Autowired
    private SqlCallAbsence sqlCallAbsence;
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
    @RequestMapping(value = "/workerId/{workerId}", method = RequestMethod.PUT)
    public @ResponseBody Integer saveWorklog(@PathVariable("workerId") Integer workerId, @RequestBody SaveAbsenceRequest request) {
        return this.sqlCallAbsence.saveAbsence(workerId, request);
    }
    
    @Async
    @RequestMapping(value = "/workerId/{workerId}", method = RequestMethod.GET)
    public @ResponseBody List<GetAbsenceResponse> getWorklog(@PathVariable Integer workerId) {
        return this.sqlCallAbsence.getAbsence(workerId);
    }
}
