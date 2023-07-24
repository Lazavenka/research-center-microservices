package com.roger.researchcenter.service.impl;

import com.roger.researchcenter.mail.MailMessageBuilder;
import com.roger.researchcenter.mail.MailPropertiesProvider;
import com.roger.researchcenter.mail.MailThread;
import com.roger.researchcenter.model.User;
import com.roger.researchcenter.service.ConfirmationService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailConfirmationService implements ConfirmationService {
    private MessageSource messageSource;
    private MailPropertiesProvider propertiesProvider;
    private JavaMailSender mailSender;

    @Override
    public void sendConfirmation(User user, String token) {
        Properties props = propertiesProvider.getProperties();
        String subject = MailMessageBuilder.buildSubject(messageSource);
        String body = MailMessageBuilder.buildMessage(token, messageSource);
        String recipient = user.getEmail();
        MailThread mailOperator = new MailThread(recipient, subject, body, props, mailSender);
        mailOperator.start();
    }
}
