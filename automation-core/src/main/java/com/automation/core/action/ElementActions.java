package com.automation.core.action;

import com.automation.core.com.DriverControl;
import com.automation.core.com.Locator;
import com.automation.core.config.SwipeConfig;
import com.automation.core.enums.Platform;
import com.automation.core.interfaces.iaction.IAction;
import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import static com.automation.core.action.ActionPage.getElementAttribute;
import static com.automation.core.action.ActionPage.performElementAction;
import static com.automation.core.action.DriverAction.withDriver;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

public class ElementActions implements IAction {
    private static final Logger LOGGER = getLogger();

    public static IAction onElement(final Locator locator) {
        return new ElementActions(locator);
    }

    protected final Locator locator;
    protected SwipeConfig swipeConfig;

    ElementActions(final Locator locator) {
        this.locator = locator;
        if (DriverControl.getInstance().getPlatform() != Platform.WEB) {
            this.swipeConfig = new SwipeConfig();
            //implement later
        }
    }

    @Override
    public void clear() {
        LOGGER.traceEntry();
        LOGGER.info("Clearing element located: {}", this.locator.getName());
        performElementAction(WebElement::clear, this.locator);
        LOGGER.traceExit();
    }

    @Override
    public String getAttribute(String attribute) {
        LOGGER.traceEntry();
        LOGGER.info("Getting attribute {} from element located: {}", attribute, this.locator.getName());
        return LOGGER.traceExit(getElementAttribute(e -> e.getAttribute(attribute), this.locator, ""));
    }

    @Override
    public String getStyle(String styleName) {
        LOGGER.traceEntry();
        LOGGER.info("Getting style {} from element located: {}", styleName, this.locator.getName());
        return LOGGER.traceExit(getElementAttribute(e -> e.getCssValue(styleName), this.locator, ""));
    }

    @Override
    public String getText() {
        LOGGER.traceEntry();
        LOGGER.info("Getting text from element located: {}", this.locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::getText, this.locator, ""));
    }

    @Override
    public boolean isDisplayed() {
        LOGGER.traceEntry();
        LOGGER.info("Checking if element located: {} is displayed", this.locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::isDisplayed, this.locator, false));
    }

    @Override
    public boolean isEnabled() {
        LOGGER.traceEntry();
        LOGGER.info("Checking if element located: {} is displayed", this.locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::isEnabled, this.locator, false));
    }

    @Override
    public boolean isSelected() {
        LOGGER.traceEntry();
        LOGGER.info("Checking if element located: {} is displayed", this.locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::isSelected, this.locator, false));
    }

    @Override
    public void scrollIntoView() {
        LOGGER.info("Scrolling element located: {} into view", this.locator.getName());
        performElementAction(e -> withDriver().executeScript("arguments[0].scrollIntoView(true);", e), this.locator);
    }

    @Override
    public StringSubject verifyAttribute(String attribute) {
        LOGGER.traceEntry();
        LOGGER.info("Verifying attribute {} from element located: {}", attribute, this.locator.getName());
        return LOGGER.traceExit(assertThat(getAttribute(attribute)));
    }

    @Override
    public BooleanSubject verifyIsEnabled() {
        LOGGER.traceEntry();
        LOGGER.info("Verifying if element located: {} is enabled", this.locator.getName());
        return LOGGER.traceExit(assertThat(isEnabled()));
    }

    @Override
    public BooleanSubject verifyIsSelected() {
        LOGGER.traceEntry();
        LOGGER.info("Verifying if element located: {} is selected", this.locator.getName());
        return LOGGER.traceExit(assertThat(isSelected()));
    }

    @Override
    public StringSubject verifyStyle(String styleName) {
        LOGGER.traceEntry();
        LOGGER.info("Verifying style {} from element located: {}", styleName, this.locator.getName());
        return LOGGER.traceExit(assertThat(getStyle(styleName)));
    }

    @Override
    public StringSubject verifyText() {
        LOGGER.traceEntry();
        LOGGER.info("Verifying text from element located: {}", this.locator.getName());
        return LOGGER.traceExit(assertThat(getText().trim()));
    }
}

