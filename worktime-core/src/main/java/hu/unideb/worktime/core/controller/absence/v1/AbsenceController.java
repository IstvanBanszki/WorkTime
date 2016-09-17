
package hu.unideb.worktime.core.controller.absence.v1;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.absence.AbsenceDataResponse;
import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import hu.unideb.worktime.api.model.absence.AbsenceRequest;
import hu.unideb.worktime.core.export.IExportService;
import hu.unideb.worktime.jdbc.absence.SqlCallAbsence;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class AbsenceController {

    @Autowired private SqlCallAbsence sqlCallAbsence;
    @Autowired private IExportService exportService;
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
    @RequestMapping(value = "/absence/v1/workerId/{workerId}", method = RequestMethod.PUT)
    public @ResponseBody SaveResult saveAbsence(@PathVariable("workerId") Integer workerId, @RequestBody AbsenceRequest request) {
        return this.sqlCallAbsence.saveAbsence(workerId, request);
    }
    
    @Async
    @RequestMapping(value = "/absence/v1/workerId/{workerId}/dateFilter/{dateFilter}", method = RequestMethod.GET)
    public @ResponseBody List<AbsenceResponse> getAbsence(@PathVariable Integer workerId, @PathVariable("dateFilter") String request) {
        return this.sqlCallAbsence.getAbsence(workerId, request);
    }
    
    @Async
    @RequestMapping(value = "/absencedata/v1/workerId/{workerId}", method = RequestMethod.GET)
    public @ResponseBody List<AbsenceDataResponse> getAbsenceData(@PathVariable Integer workerId) {
        return this.sqlCallAbsence.getAbsenceData(workerId);
    }
    
    @Async
    @RequestMapping(value = "/absence/v1/id/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Integer deleteAbsence(@PathVariable Integer id) {
        return this.sqlCallAbsence.deleteAbsence(id);
    }
    
    @Async
    @RequestMapping(value = "/absence/v1/id/{id}", method = RequestMethod.PUT)
    public @ResponseBody Integer editAbsence(@PathVariable Integer id, @RequestBody AbsenceRequest request) {
        return this.sqlCallAbsence.editAbsence(id, request);
    }

    @Async
    @RequestMapping(value = "/absence/v1/workerId/{workerId}/dateFilter/{dateFilter}/type/{type}/export", method = RequestMethod.GET)
    public void exportAbsences(@PathVariable("workerId") Integer workerId, @PathVariable("dateFilter") String request, 
            @PathVariable("type") Integer excelType, HttpServletResponse response) {
        this.exportService.exportAbsences(workerId, request, excelType, response);
    }
}
