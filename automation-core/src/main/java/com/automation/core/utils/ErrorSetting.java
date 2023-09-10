package com.automation.core.utils;

public class ErrorSetting extends Error {
    public ErrorSetting(final String message) {
        super(message);
    }

    public ErrorSetting(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
