package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.administration.Employee;
import java.util.List;

public interface IAdditionService {
    
    SaveResult createUser(User request);
    SaveResult createWorker(Worker request);
    List<Employee> getSuperiors();

}
