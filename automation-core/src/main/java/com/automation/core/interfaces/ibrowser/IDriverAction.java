package com.automation.core.interfaces.ibrowser;

import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.function.Function;

public interface IDriverAction {
    <T> T executeScript(final String script, final Object... args);

    void pause(final Duration time);

    <T> void waitUntil(final Function<WebDriver, T> condition);
}
