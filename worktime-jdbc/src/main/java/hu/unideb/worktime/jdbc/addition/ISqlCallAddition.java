package hu.unideb.worktime.jdbc.addition;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.administration.Employee;
import java.util.List;

public interface ISqlCallAddition {
    
    SaveResult saveUser(User user, String password);
    SaveResult saveWorker(Worker worker);
    List<Employee> getSuperiors();
    
}
