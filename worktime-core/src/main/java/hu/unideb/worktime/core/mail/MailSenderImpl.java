package hu.unideb.worktime.core.mail;

import hu.unideb.worktime.api.model.mail.Mail;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderImpl implements IMailSender {
    
    private Logger logger = LoggerFactory.getLogger(MailSenderImpl.class);
    
    @Autowired private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Mail mail) {
        this.logger.info("Send Mail message, with following properties {}", mail);
        MimeMessage message = this.javaMailSender.createMimeMessage();
        
        try {
            message.setFrom(new InternetAddress(mail.getFromAddress()));
            for(String address : mail.getToAddress()) {
                message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(address));
            }
            for(String address : mail.getCcAddress()) {
                message.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(address));
            }
            message.setSubject(mail.getSubject());
            
            String body = mail.getTemplateBody();
            MimeMultipart multipart = new MimeMultipart();
            if (body != null) {
                MimeBodyPart bodyPart = new MimeBodyPart();
                bodyPart.setText(body, "UTF-8", "plain");
                bodyPart.addHeader("Content-Type", "text/html");
                multipart.addBodyPart(bodyPart);
            }
            message.setContent(multipart);
            message.setSentDate(new Date());
        } catch (MessagingException ex) {
            this.logger.error("There's an exception during sending {}", ex);
        }
        this.javaMailSender.send(message);
    }

}
