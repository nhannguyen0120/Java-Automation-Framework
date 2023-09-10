package com.automation.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {

    EMPTY_BROWSER("Browser cannot be empty in the config"),

    INVALID_BROWSER("Ivalid browser"),

    EXCEPTION_ERROR("EXCEPTION ERROR: {0}"),

    ELEMENT_NOT_FOUND("Element not found");
    private final String messageText;
}