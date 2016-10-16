package hu.unideb.worktime.core.service.impl;

import hu.unideb.worktime.api.model.Worker;
import hu.unideb.worktime.api.model.addition.UserExtended;
import hu.unideb.worktime.api.model.mail.Mail;
import hu.unideb.worktime.core.mail.IMailSender;
import hu.unideb.worktime.core.service.IMailSenderService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements IMailSenderService {

    @Autowired private IMailSender mailSender;

    private static final String ADMIN_ADDRESS = "banszki.anarchy@gmail.com";
    private static final String USER_CREATION_SUBJECT = "WorkTime User Registration";
    private static final String WORKER_CREATION_SUBJECT = "WorkTime Worker Registration";
    private static final String TEMPLATE_BEGIN = "Dear ";
    private static final String TEMPLATE_PASS = "Your new password: ";

    private Logger logger = LoggerFactory.getLogger(MailSenderServiceImpl.class);
    
    @Override
    public void sendEmailForNewlyRegisteredUser(UserExtended request, String passwordForSave) {
        List<String> toAddress = new ArrayList<>();
        toAddress.add(request.getEmailAddress());

        StringBuilder template = new StringBuilder(TEMPLATE_BEGIN);
        template.append(request.getLoginName()).append("\n\n");
        template.append(TEMPLATE_PASS).append(request.getPassword());

        Mail mail = new Mail(ADMIN_ADDRESS, toAddress, new ArrayList<>(), USER_CREATION_SUBJECT, template.toString());
        try {
            this.mailSender.sendEmail(mail);
        } catch(Exception e) {
            this.logger.error(e.getMessage());
        }
    }

    @Override
    public void sendEmailForNewlyRegisteredWorker(Worker request) {
        List<String> toAddress = new ArrayList<>();
        toAddress.add(request.getEmailAddress());

        StringBuilder template = new StringBuilder(TEMPLATE_BEGIN);
        template.append(request.getFirstName()).append("\n\n");
        template.append("Please check you Profile data in the system!");

        Mail mail = new Mail(ADMIN_ADDRESS, toAddress, new ArrayList<>(), WORKER_CREATION_SUBJECT, template.toString());
        try {
            this.mailSender.sendEmail(mail);
        } catch(Exception e) {
            this.logger.error(e.getMessage());
        }
    }

}
