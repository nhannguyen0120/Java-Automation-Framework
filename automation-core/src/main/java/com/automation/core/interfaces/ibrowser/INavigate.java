package com.automation.core.interfaces.ibrowser;

public interface INavigate {

    void back();

    void forward();

    String getUrl();

    void refresh();

    void to(final String url);
}
