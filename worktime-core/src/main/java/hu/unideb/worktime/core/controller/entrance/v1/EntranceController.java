package hu.unideb.worktime.core.controller.entrance.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/entrance/v1", produces = "application/json")
public class EntranceController {
    
    private Logger logger = LoggerFactory.getLogger(EntranceController.class);
    
    @Async
    @RequestMapping(value = "/entry/workerIds/{workerId}", method = RequestMethod.GET)
    public void getProfile(@PathVariable("workerId") Integer workerId) {
        this.logger.info("workerId {}", workerId);
    }

}
