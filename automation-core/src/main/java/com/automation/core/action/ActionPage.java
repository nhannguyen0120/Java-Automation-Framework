package com.automation.core.action;

import com.automation.core.action.element.finder.Finder;
import com.automation.core.com.BrowserControl;
import com.automation.core.com.Locator;
import com.automation.core.enums.Message;
import com.automation.core.enums.WaitStrategy;
import com.automation.core.utils.ErrorHandler;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ActionPage {
    private static final Logger LOGGER = getLogger();

    public static <D extends WebDriver> void performAction(final Consumer<D> action) {
        try {
            action.accept(BrowserControl.getDriver());
        } catch (final WebDriverException e) {
            ErrorHandler.throwError(Message.EXCEPTION_ERROR, e);
        }
    }

    public static <D extends WebDriver, E> E getDriverAttribute(final Function<D, E> action, final E defaultValue) {
        try {
            return action.apply(BrowserControl.getDriver());
        } catch (final Exception e) {
            if (defaultValue != null) {
                return defaultValue;
            } else {
                throw e;
            }
        }
    }

    public static void performElementAction(final Consumer<WebElement> action, final Locator locator) {
        LOGGER.traceEntry();
        try {
            action.accept(Finder.find(locator, WaitStrategy.CLICKABLE));
        } catch (final WebDriverException e) {
            ErrorHandler.throwError(Message.EXCEPTION_ERROR, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    public static <D extends WebDriver> void performElementAction(final BiConsumer<D, WebElement> action, final Locator locator){
        LOGGER.traceEntry();
        try {
            action.accept(BrowserControl.getDriver(), Finder.find(locator, WaitStrategy.CLICKABLE));
        } catch (final WebDriverException e) {
            ErrorHandler.throwError(Message.EXCEPTION_ERROR, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    public static <E> E getElementAttribute(final Function<WebElement, E> action, final Locator locator, final E defaultValue) {
        LOGGER.traceExit();
        try {
            return LOGGER.traceExit(action.apply(Finder.find(locator, WaitStrategy.VISIBLE)));
        } catch (final WebDriverException e) {
            return defaultValue;
        }
    }

    public static <D extends WebDriver> void performDriverAction(final Consumer<D> action) {
        LOGGER.traceEntry();
        try {
            action.accept(BrowserControl.getDriver());
        } catch (final WebDriverException e) {
            ErrorHandler.throwError(Message.EXCEPTION_ERROR, e, e.getMessage());
        }
        LOGGER.traceExit();
    }
}

