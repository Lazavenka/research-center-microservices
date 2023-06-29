package com.roger.researchcenter.mail;

import com.roger.researchcenter.exception.CustomWebServiceException;
import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread {
    private static final Logger logger = LogManager.getLogger();
    private MimeMessage message;
    private final String recipient;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;

    public MailThread(String recipient, String subject, String mailText, Properties properties) {
        this.recipient = recipient;
        this.mailSubject = subject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void init() {
        Session mailSession = (new SessionCreator(properties)).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html; charset=utf-8");

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        } catch (AddressException e) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_EMAIL + " " + e);
        } catch (MessagingException e) {
            throw new CustomWebServiceException(ServiceLayerExceptionCodes.MAIL_CREATION_ERROR);
        }
    }

    @Override
    public void run() {
        init();
        try {
            Transport.send(message, message.getRecipients(Message.RecipientType.TO));
        } catch (MessagingException e) {
            throw new CustomWebServiceException(ServiceLayerExceptionCodes.MAIL_SENDING_ERROR + e);
        }
    }
}
