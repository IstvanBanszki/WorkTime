package hu.unideb.worktime.core.mail;

import hu.unideb.worktime.api.model.mail.Mail;

public interface IMailSender {
    
    void sendEmail(Mail mail);

}
