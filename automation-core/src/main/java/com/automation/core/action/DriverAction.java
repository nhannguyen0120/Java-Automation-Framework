package com.automation.core.action;

import com.automation.core.com.DriverControl;
import com.automation.core.interfaces.ibrowser.IDriverAction;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.function.Function;

import static com.automation.core.action.ActionPage.getDriverAttribute;
import static com.automation.core.action.ActionPage.performDriverAction;
import static org.apache.logging.log4j.LogManager.getLogger;

public class DriverAction implements IDriverAction {
    private static final DriverAction DRIVER_ACTION = new DriverAction ();

    private static final Logger LOGGER = getLogger();

    public static IDriverAction withDriver(){
        return DRIVER_ACTION;
    }

    @Override
    public <T> T executeScript(String script, Object... args) {
        LOGGER.traceEntry();
        LOGGER.info("Executing script: " + script);
        return LOGGER.traceExit((T) getDriverAttribute(driver -> ((JavascriptExecutor) driver).executeScript(script, args), null));
    }

    @Override
    public void pause(final Duration time) {
        LOGGER.traceEntry();
        performDriverAction(driver -> {
            final var action = new Actions(driver);
            action.pause(time).build().perform();
        });
        LOGGER.traceExit();
    }

    @Override
    public <T> void waitUntil(final Function<WebDriver, T> condition) {
        LOGGER.traceEntry();
        LOGGER.info("Waiting for driver action...");
        performDriverAction(driver -> {
            final var wait = DriverControl.getWait();
            wait.until(condition);
        });
    }
}