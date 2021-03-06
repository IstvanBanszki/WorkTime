package hu.unideb.worktime.core.controller.worklog.v1;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.worklog.MontlyStatRequest;
import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import hu.unideb.worktime.api.model.worklog.WorklogRequest;
import hu.unideb.worktime.core.export.IExportService;
import hu.unideb.worktime.jdbc.worklog.ISqlCallWorklog;
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
@RequestMapping(value = "/api/worklog/v1", produces = "application/json")
public class WorklogController {
    
    @Autowired private ISqlCallWorklog sqlCallWorklog;
    @Autowired private IExportService exportService;

    @Async
    @RequestMapping(value = "/worklogs/workerIds/{workerId}", method = RequestMethod.PUT)
    public @ResponseBody SaveResult saveWorklog(@PathVariable("workerId") Integer workerId, @RequestBody WorklogRequest request) {
        return this.sqlCallWorklog.saveWorklog(workerId, request);
    }

    @Async
    @RequestMapping(value = "/worklogs/workerIds/{workerId}/dateFilters/{dateFilter}", method = RequestMethod.GET)
    public @ResponseBody List<WorklogResponse> getWorklog(@PathVariable("workerId") Integer workerId, @PathVariable("dateFilter") String request) {
        return this.sqlCallWorklog.getWorklog(workerId, request);
    }

    @Async
    @RequestMapping(value = "/worklogs/workerIds/{worklogId}", method = RequestMethod.DELETE)
    public @ResponseBody Integer deleteWorklog(@PathVariable("worklogId") Integer id) {
        return this.sqlCallWorklog.deleteWorklog(id);
    }

    @Async
    @RequestMapping(value = "/worklogs/edit/workerIds/{worklogId}", method = RequestMethod.PUT)
    public @ResponseBody Integer editWorklog(@PathVariable("worklogId") Integer id, @RequestBody WorklogRequest request) {
        return this.sqlCallWorklog.editWorklog(id, request);
    }

    @Async
    @RequestMapping(value = "/worklogs/export/workerIds/{workerId}/dateFilters/{dateFilter}/types/{type}", method = RequestMethod.GET)
    public void exportWorklogs(@PathVariable("workerId") Integer workerId, @PathVariable("dateFilter") String request, 
            @PathVariable("type") Integer excelType, HttpServletResponse response) {
        this.exportService.exportWorklogs(workerId, request, excelType, response);
    }

    @Async
    @RequestMapping(value = "/monthly/statistics/workerIds/{workerId}/types/{type}/years/{year}/months/{month}", method = RequestMethod.GET)
    public void getMontlyStat(@PathVariable("workerId") Integer workerId, @PathVariable("year") Integer year, 
            @PathVariable("month") Integer month, @PathVariable("type") Integer excelType, HttpServletResponse response) {
        this.exportService.exportMonthlyStat(new MontlyStatRequest(workerId, year, month, excelType), response);
    }

}
