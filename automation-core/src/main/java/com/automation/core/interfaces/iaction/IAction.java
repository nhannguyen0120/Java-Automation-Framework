package com.automation.core.interfaces.iaction;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;

public interface IAction {

    void clear();

    String getAttribute(final String attribute);

    String getStyle(final String styleName);

    String getText();

    boolean isDisplayed();

    boolean isEnabled();

    boolean isSelected();

    void scrollIntoView();

    StringSubject verifyAttribute(final String attribute);

    BooleanSubject verifyIsEnabled();

    BooleanSubject verifyIsSelected();

    StringSubject verifyStyle(final String styleName);

    StringSubject verifyText();
}