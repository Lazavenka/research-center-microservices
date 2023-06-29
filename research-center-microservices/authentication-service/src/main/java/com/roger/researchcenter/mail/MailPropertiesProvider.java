package com.roger.researchcenter.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailPropertiesProvider {
    private static final String SMTP_AUTH_PROP = "mail.smtp.auth";
    private static final String TRANSPORT_PROTOCOL_PROP = "mail.transport.protocol";
    private static final String SMTP_SSL_TRUST_PROP = "mail.smtp.ssl.trust";
    //private static final String SMTP_SSL_PROTOCOLS_PROP = "mail.smtp.ssl.protocols";
    private static final String SMTP_STARTTLS_ENABLE_PROP = "mail.smtp.starttls.enable";
    private static final String SMTP_HOST_PROP = "mail.smtp.host";
    private static final String SMTP_PORT_PROP = "mail.smtp.port";
    public static final String MAIL_USERNAME_PROP = "mail.user.name";
    public static final String MAIL_PASSWORD_PROP = "mail.user.password";

    @Value("${" + SMTP_AUTH_PROP + "}")
    private String smtpAuth;

    @Value("${" + TRANSPORT_PROTOCOL_PROP + "}")
    private String transportProtocol;

    @Value("${" + SMTP_SSL_TRUST_PROP + "}")
    private String smtpSslTrust;

    //@Value("${" + SMTP_SSL_PROTOCOLS_PROP + "}")
    //private String smtpSslProtocols;

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
        properties.put(TRANSPORT_PROTOCOL_PROP, transportProtocol);
        properties.put(SMTP_SSL_TRUST_PROP, smtpSslTrust);
        //properties.put(SMTP_SSL_PROTOCOLS_PROP, smtpSslProtocols);
        properties.put(SMTP_STARTTLS_ENABLE_PROP, smtpStartTlsEnable);
        properties.put(SMTP_HOST_PROP, smtpHost);
        properties.put(SMTP_PORT_PROP, smtpPort);
        properties.put(MAIL_USERNAME_PROP, mailUsername);
        properties.put(MAIL_PASSWORD_PROP, mailPassword);

        return properties;
    }

}
