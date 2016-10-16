package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.addition.UserExtended;
import hu.unideb.worktime.api.model.administration.Employee;
import java.util.List;

public interface IAdditionnService {

    SaveResult createUser(UserExtended request);
    SaveResult createWorker(Worker request);
    List<Employee> getSuperior();

}
