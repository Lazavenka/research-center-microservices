package com.roger.researchcenter.mail;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MailMessageBuilder {
    private static final String LOCALIZED_SUBJECT = "locale.subject";
    private static final String LOCALIZED_MESSAGE = "locale.message";
    private MailMessageBuilder(){
    }

    public static String buildMessage(String token, MessageSource messageSource){
        return messageSource.getMessage(LOCALIZED_MESSAGE, null, LocaleContextHolder.getLocale()).formatted(token);
    }

    public static String buildSubject(MessageSource messageSource) {
        return messageSource.getMessage(LOCALIZED_SUBJECT, null, LocaleContextHolder.getLocale());

    }
}
