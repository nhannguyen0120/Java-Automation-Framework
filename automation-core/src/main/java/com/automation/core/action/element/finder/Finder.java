package com.automation.core.action.element.finder;

import com.automation.core.com.BrowserControl;
import com.automation.core.com.DriverControl;
import com.automation.core.com.Locator;
import com.automation.core.enums.Message;
import com.automation.core.enums.WaitStrategy;
import com.automation.core.utils.ErrorHandler;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.text.MessageFormat.format;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Finder {
    private static final Logger LOGGER = getLogger();

    public static WebElement find(final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry();
        final var elements = finds(locator, waitStrategy);
        if (elements.isEmpty()) {
            ErrorHandler.throwError(Message.ELEMENT_NOT_FOUND, locator.getName(), DriverControl.getInstance().getPlatform());
        }
        if (locator.getFilter() != null) {
            return elements.stream().filter(locator.getFilter())
                    .findFirst().orElseThrow(()
                            -> (new Error(
                            format(Message.ELEMENT_NOT_FOUND.getMessageText(),
                                    locator.getName(),
                                    DriverControl.getInstance().getPlatform()))));
        }
        return elements.get(locator.getIndex());
    }

    public static List<WebElement> finds(final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry();
        final var driver = BrowserControl.getDriver();
        final List<WebElement> elements;
        waitForElement(locator, waitStrategy);
        elements = finds(driver, locator);
        return LOGGER.traceExit(elements);
    }

    private static <D extends WebDriver> List<WebElement> finds(final D driver, final Locator locator) {
        LOGGER.traceEntry();
        return LOGGER.traceExit(finds(driver, null, locator));
    }

    private static <D extends WebDriver> List<WebElement> finds(final D driver, final WebElement parent, final Locator locator) {
        LOGGER.traceEntry();
        final var platformLocator = locator.getLocator();
        if (platformLocator == null) {
            ErrorHandler.throwError(Message.ELEMENT_NOT_FOUND, locator.getName(), DriverControl.getInstance().getPlatform());
        }
        return LOGGER.traceExit(
                parent != null ? parent.findElements(locator.getLocator()) : driver.findElements(locator.getLocator())
        );
    }

    public static void waitForElement(final Locator locator, final WaitStrategy waitStrategy) {
        try {
            final var wait = DriverControl.getWait();
            switch (waitStrategy) {
                case CLICKABLE -> wait.until(elementToBeClickable(locator.getLocator()));
                case VISIBLE -> wait.until(visibilityOfElementLocated(locator.getLocator()));
            }
        } catch (final TimeoutException e) {
            ErrorHandler.throwError(Message.ELEMENT_NOT_FOUND, e, locator.getName(), DriverControl.getInstance().getPlatform());
        }
    }
}
