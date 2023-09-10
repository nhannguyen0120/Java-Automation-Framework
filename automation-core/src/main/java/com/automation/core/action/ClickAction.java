package com.automation.core.action;

import com.automation.core.com.DriverControl;
import com.automation.core.com.Locator;
import com.automation.core.enums.Platform;
import com.automation.core.interfaces.iaction.IClickable;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.automation.core.action.ActionPage.performElementAction;
import static org.apache.logging.log4j.LogManager.getLogger;

public class ClickAction extends ElementActions implements IClickable {

    private static final Logger LOGGER = getLogger();

    ClickAction(Locator locator) {
        super(locator);
    }

    public static IClickable withMouse(final Locator locator) {
        return new ClickAction(locator);
    }

    @Override
    public void click() {
        LOGGER.traceEntry();
        LOGGER.info("Clicking on element: {}", this.locator.getName());
        if (DriverControl.getInstance().getPlatform() == Platform.WEB) {
            scrollIntoView();
            performElementAction(WebElement::click, this.locator);
        }
        LOGGER.traceExit();
    }

    @Override
    public void clickAndHold() {
        LOGGER.traceEntry();
        LOGGER.info("Clicking and hold on element: {}", this.locator.getName());
        if (DriverControl.getInstance().getPlatform() == Platform.WEB) {
            performElementAction((driver, element) -> {
                final var actions = new Actions(driver);
                actions.clickAndHold(element).perform();
            }, this.locator);
        }
        LOGGER.traceExit();
    }

    @Override
    public void doubleClick() {
        LOGGER.traceEntry();
        LOGGER.info("Double clicking on element: {}", this.locator.getName());
        if (DriverControl.getInstance().getPlatform() == Platform.WEB) {
            performElementAction((driver, element) -> {
                final var actions = new Actions(driver);
                actions.doubleClick(element).perform();
            }, this.locator);
        }
        LOGGER.traceExit();
    }

    @Override
    public void hover() {
        LOGGER.traceEntry();
        LOGGER.info("Hovering on element: {}", this.locator.getName());
        if (DriverControl.getInstance().getPlatform() == Platform.WEB) {
            performElementAction((driver, element) -> {
                final var actions = new Actions(driver);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
                actions.moveToElement(element).perform();
            }, this.locator);
        }
        LOGGER.traceExit();
    }

    @Override
    public void rightClick() {
        LOGGER.traceEntry();
        LOGGER.info("Right clicking on element: {}", this.locator.getName());
        performElementAction((driver, element) -> {
            final var actions = new Actions(driver);
            actions.contextClick(element).perform();
        }, this.locator);
        LOGGER.traceExit();
    }

    @Override
    public void submit() {
        LOGGER.traceEntry();
        LOGGER.info("Submitting form: {}", this.locator.getName());
        if (DriverControl.getInstance().getPlatform() == Platform.WEB) {
            performElementAction(WebElement::submit, this.locator);
        }
        LOGGER.traceExit();
    }
}
