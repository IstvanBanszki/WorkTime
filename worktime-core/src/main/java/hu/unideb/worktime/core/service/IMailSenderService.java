package hu.unideb.worktime.core.service;

import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.addition.UserExtended;

public interface IMailSenderService {

    void sendEmailForNewlyRegisteredUser(UserExtended request, String passwordForSave);
    void sendEmailForNewlyRegisteredWorker(Worker request);
}
