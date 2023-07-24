package com.roger.researchcenter.mail;

import com.roger.researchcenter.exception.CustomWebServiceException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;
@Slf4j
public class MailThread extends Thread {
    private SimpleMailMessage simpleMailMessage;
    private final String recipient;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;
    private final JavaMailSender mailSender;

    public MailThread(String recipient, String subject, String mailText, Properties properties, JavaMailSender mailSender) {
        this.recipient = recipient;
        this.mailSubject = subject;
        this.mailText = mailText;
        this.properties = properties;
        this.mailSender = mailSender;
    }

    private void init() {
        simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(properties.getProperty(MailPropertiesProvider.MAIL_USERNAME_PROP));
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(mailSubject);
        simpleMailMessage.setText(mailText);
    }

    @Override
    public void run() {
        init();
        try {
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.error(String.valueOf(e));
            throw new CustomWebServiceException(ServiceLayerExceptionCodes.MAIL_SENDING_ERROR + e);
        }
    }
}
