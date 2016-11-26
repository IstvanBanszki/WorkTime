package hu.unideb.worktime.core.service.impl;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.User;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.core.security.IEncryptionUtility;
import hu.unideb.worktime.core.service.IAdditionService;
import hu.unideb.worktime.jdbc.addition.ISqlCallAddition;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdditionServiceImpl implements IAdditionService {
    
    @Autowired private ISqlCallAddition sqlCallAddition;
    @Autowired private IEncryptionUtility encryptionUtility;
    
    @Override
    public SaveResult createUser(User request) {
        String passwordForSave = this.encryptionUtility.encryptPassword(request.getPassword());
        SaveResult saveResult = this.sqlCallAddition.saveUser(request, passwordForSave);
        return saveResult;
    }

    @Override
    public SaveResult createWorker(Worker request){
        return this.sqlCallAddition.saveWorker(request);
    }

    @Override
    public List<Employee> getSuperiors() {
        return this.sqlCallAddition.getSuperiors();
    }

}
