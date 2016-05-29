package hu.unideb.worktime.core.controller.worklog.v1;

import hu.unideb.worktime.api.model.worklog.SaveWorklogRequest;
import hu.unideb.worktime.jdbc.worklog.SqlCallSaveWorklog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/worklog/v1")
public class WorklogController {
    
    @Autowired
    private SqlCallSaveWorklog sqlCallSaveWorklog;
    private Logger logger;

    public WorklogController() {
        this.logger = LoggerFactory.getLogger(WorklogController.class);
    }
    /*
    --------------------
    Example JSON content
    --------------------
    {
        "description": "test description",
        "begin": "2016-05-20T08:30:00.000",
        "end": "2016-05-20T16:30:00.000",
        "workerId": 1
    }
     */
    @Async
    @RequestMapping(value = "/saveworklog", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public @ResponseBody Integer saveWorklog(@RequestBody SaveWorklogRequest request) {
        Integer result = null;

        this.logger.info("Calling saveWorklog webservice with the following key: {}", request);
        result = this.sqlCallSaveWorklog.saveWorklog(request);
        this.logger.info("Result of saveWorklog webservice: {}", result);

        return result;
    }
}
