package hu.unideb.worktime.core.service.impl;

import hu.unideb.worktime.api.model.SaveResult;
import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.addition.UserExtended;
import hu.unideb.worktime.api.model.administration.Employee;
import hu.unideb.worktime.core.security.IEncryptionUtility;
import hu.unideb.worktime.core.service.IAdditionnService;
import hu.unideb.worktime.core.service.IMailSenderService;
import hu.unideb.worktime.jdbc.addition.ISqlCallAddition;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdditionnServiceImpl implements IAdditionnService {

    @Autowired private ISqlCallAddition sqlCallAddition;
    @Autowired private IEncryptionUtility encryptionUtility;
    @Autowired private IMailSenderService mailSenderService;

    private Logger logger = LoggerFactory.getLogger(AdditionnServiceImpl.class);
    
    @Override
    public SaveResult createUser(UserExtended request) {
        String passwordForSave = this.encryptionUtility.generateRandomPassword();
        this.logger.info("Newly generated password {}, for the following user {}", passwordForSave, request);
        SaveResult saveResult = this.sqlCallAddition.saveUser(request, passwordForSave);
        this.mailSenderService.sendEmailForNewlyRegisteredUser(request, passwordForSave);
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
