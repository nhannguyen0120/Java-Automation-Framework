package com.automation.core.com;

import com.automation.core.interfaces.ibrowser.INavigate;

import static com.automation.core.action.ActionPage.performAction;

public class Navigate implements INavigate {

    public static INavigate navigate() {
        return new Navigate();
    }

    @Override
    public void back() {
        performAction(driver -> driver.navigate().back());
    }

    @Override
    public void forward() {
        performAction(driver -> driver.navigate().forward());
    }

    @Override
    public String getUrl() {
//        return getDriverAttribute(WebDriver::getCurrentUrl, EMPTY);
        return "";
    }

    @Override
    public void refresh() {
        performAction(driver -> driver.navigate().refresh());
    }

    @Override
    public void to(String url) {
        performAction(driver -> driver.get(url));
    }
}