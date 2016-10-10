
package hu.unideb.worktime.core.controller.absence.v1;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.absence.AbsenceDataResponse;
import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import hu.unideb.worktime.api.model.absence.AbsenceRequest;
import hu.unideb.worktime.core.export.IExportService;
import hu.unideb.worktime.jdbc.absence.ISqlCallAbsence;
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
@RequestMapping(value = "/api/absence/v1", produces = "application/json")
public class AbsenceController {

    @Autowired private ISqlCallAbsence sqlCallAbsence;
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
    @RequestMapping(value = "/workers/{workerId}", method = RequestMethod.PUT)
    public @ResponseBody SaveResult saveAbsence(@PathVariable("workerId") Integer workerId, @RequestBody AbsenceRequest request) {
        return this.sqlCallAbsence.saveAbsence(workerId, request);
    }
    
    @Async
    @RequestMapping(value = "/workers/{workerId}/dates/{dateFilter}/absence", method = RequestMethod.GET)
    public @ResponseBody List<AbsenceResponse> getAbsence(@PathVariable("workerId") Integer workerId, @PathVariable("dateFilter") String request) {
        return this.sqlCallAbsence.getAbsence(workerId, request);
    }
    
    @Async
    @RequestMapping(value = "/workers/{workerId}/absenceData", method = RequestMethod.GET)
    public @ResponseBody List<AbsenceDataResponse> getAbsenceData(@PathVariable("workerId") Integer workerId) {
        return this.sqlCallAbsence.getAbsenceData(workerId);
    }
    
    @Async
    @RequestMapping(value = "/absences/{absenceId}", method = RequestMethod.DELETE)
    public @ResponseBody Integer deleteAbsence(@PathVariable("absenceId") Integer id) {
        return this.sqlCallAbsence.deleteAbsence(id);
    }
    
    @Async
    @RequestMapping(value = "/absences/{absenceId}/edit", method = RequestMethod.PUT)
    public @ResponseBody Integer editAbsence(@PathVariable("absenceId") Integer id, @RequestBody AbsenceRequest request) {
        return this.sqlCallAbsence.editAbsence(id, request);
    }

    @Async
    @RequestMapping(value = "/workers/{workerId}/dates/{dateFilter}/types/{type}/export", method = RequestMethod.GET)
    public void exportAbsences(@PathVariable("workerId") Integer workerId, @PathVariable("dateFilter") String request, 
            @PathVariable("type") Integer excelType, HttpServletResponse response) {
        this.exportService.exportAbsences(workerId, request, excelType, response);
    }
}
