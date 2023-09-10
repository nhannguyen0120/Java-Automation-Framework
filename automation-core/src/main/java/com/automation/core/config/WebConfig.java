package com.automation.core.config;

import com.automation.core.enums.Browser;
import com.automation.core.enums.WindowSizeType;
import lombok.Data;
import org.openqa.selenium.Dimension;

import java.util.List;
import java.util.Map;

@Data
public class WebConfig {
    private String baseUrl = "https://www.webpagetest.org/";
    private Browser browser = getBrowserInConfig();
    private List<String> browserOptions;
    private Map<String, Object> capabilities;
    private boolean headless = Boolean.parseBoolean(GetConfig.getConfig("headless"));
    private String platform;
    private Dimension size = new Dimension(Integer.parseInt(GetConfig.getConfig("width")), Integer.parseInt(GetConfig.getConfig("height")));
    private WindowSizeType windowSize = windowSizeType();

    private static WebConfig instance = null;

    public static WebConfig initInstance() {
        if (instance == null)
            instance = new WebConfig();
        return instance;
    }

    private Browser getBrowserInConfig() {
        return Browser.valueOf(GetConfig.getConfig("browser"));
    }

    private WindowSizeType windowSizeType() {
        return WindowSizeType.valueOf(GetConfig.getConfig("windowSize"));
    }

}