package com.automation.core.com;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

@Getter
@ToString
@Builder(builderMethodName = "buildLocator")
public class Locator {
    private String name;
    private int index;
    private Predicate<WebElement> filter;
    private By android;
    private By ios;
    private By web;

    // Android and ios not implemented yet
    public By getLocator() {
        return this.web;
    }
}

