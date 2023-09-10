package com.automation.core.action;

import com.automation.core.com.Locator;
import com.automation.core.interfaces.iaction.ITextBox;
import org.apache.logging.log4j.Logger;

import static com.automation.core.action.ActionPage.performElementAction;
import static org.apache.logging.log4j.LogManager.getLogger;

public class TextBoxActions extends ClickAction implements ITextBox {
    private static final Logger LOGGER = getLogger ();

    public static ITextBox onTextBox(final Locator locator){ return new TextBoxActions(locator);}
    TextBoxActions(Locator locator) {
        super(locator);
    }

    @Override
    public void enterText(String text) {
        LOGGER.traceEntry();
        LOGGER.info("Entering text {} to element {}", text, this.locator.getName());
        performElementAction(e -> {
            e.sendKeys(text);
        }, this.locator);
        LOGGER.traceExit();
    }
}
