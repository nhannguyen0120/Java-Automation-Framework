package com.automation.core.config;

import lombok.Data;

@Data
public class TimeOutConfig {
    private int explicitWait = 10;
    private long highlightDelay = 100;
    private int implicitWait = 1;
    private int pageLoadTimeout = 30;
    private int scriptTimeout = 10;
    private int timeout = 10;
}