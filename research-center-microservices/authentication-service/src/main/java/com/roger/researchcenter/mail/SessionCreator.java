package com.roger.researchcenter.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {
    private final String userName;
    private final String userPassword;

    private final Properties sessionProperties;

    public SessionCreator(Properties properties){
        this.sessionProperties = properties;
        this.userName = properties.getProperty(MailPropertiesProvider.MAIL_USERNAME_PROP);
        this.userPassword = properties.getProperty(MailPropertiesProvider.MAIL_PASSWORD_PROP);
    }
    public Session createSession(){
        return Session.getDefaultInstance(sessionProperties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
