package com.automation.core.utils;

import com.automation.core.enums.Message;

import static java.lang.String.format;

public class ErrorHandler {

    //log4j logging (later)

    public static <T> T requireNotNull(final T subject, final Message throwMessage, final Object... args) {
        if (subject == null) {
            throwError(throwMessage, args);
        }
        return subject;
    }

    public static void throwError(final Message message, final Object... args) {
        throw new ErrorSetting(format(message.getMessageText(), args));
    }
}