package com.roger.researchcenter.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailPropertiesProvider {
    private static final String SMTP_AUTH_PROP = "spring.mail.properties.mail.smtp.auth";
    private static final String SMTP_STARTTLS_ENABLE_PROP = "spring.mail.properties.mail.smtp.starttls.enable";
    private static final String SMTP_HOST_PROP = "spring.mail.host";
    private static final String SMTP_PORT_PROP = "spring.mail.port";
    public static final String MAIL_USERNAME_PROP = "spring.mail.username";
    public static final String MAIL_PASSWORD_PROP = "spring.mail.password";

    @Value("${" + SMTP_AUTH_PROP + "}")
    private String smtpAuth;

    @Value("${" + SMTP_STARTTLS_ENABLE_PROP + "}")
    private String smtpStartTlsEnable;

    @Value("${" + SMTP_HOST_PROP + "}")
    private String smtpHost;

    @Value("${" + SMTP_PORT_PROP + "}")
    private String smtpPort;

    @Value("${" + MAIL_USERNAME_PROP + "}")
    private String mailUsername;

    @Value("${" + MAIL_PASSWORD_PROP + "}")
    private String mailPassword;

    public Properties getProperties(){
        Properties properties = new Properties();
        properties.put(SMTP_AUTH_PROP, smtpAuth);
        properties.put(SMTP_STARTTLS_ENABLE_PROP, smtpStartTlsEnable);
        properties.put(SMTP_HOST_PROP, smtpHost);
        properties.put(SMTP_PORT_PROP, smtpPort);
        properties.put(MAIL_USERNAME_PROP, mailUsername);
        properties.put(MAIL_PASSWORD_PROP, mailPassword);

        return properties;
    }

}
