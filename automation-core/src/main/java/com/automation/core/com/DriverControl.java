package com.automation.core.com;

import com.automation.core.config.GetConfig;
import com.automation.core.config.TimeOutConfig;
import com.automation.core.enums.Platform;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static java.time.Duration.ofSeconds;
import static org.apache.logging.log4j.LogManager.getLogger;

public final class DriverControl {
    private final TimeOutConfig timeout = new TimeOutConfig();

    @Getter
    @Setter
    private static WebDriverWait wait;

    @Getter
    private final Platform platform;

    private static final Logger LOGGER = getLogger();

    DriverControl() {
        this.platform = getBrowserInConfig();
    }

    public static DriverControl getInstance() {
        return Objects.requireNonNullElseGet(null, DriverControl::new);
    }

    public void setUp() {
        switch (this.platform) {
            case WEB -> {
                BrowserControl browserManager = new BrowserControl();
                browserManager.selectBrowser();
                setDriverWait(timeout);
            }
            case API, ANDROID, IOS -> LOGGER.info("Not implemented yet");
        }
    }

    public void tearDown() {
        if (BrowserControl.getDriver() != null) {
            BrowserControl.getDriver().close();
            BrowserControl.getDriver().quit();
        }
    }

    private void setDriverWait(final TimeOutConfig timeOutConfig) {
        final var driver = BrowserControl.getDriver();
        final var timeouts = driver.manage().timeouts();
        timeouts.implicitlyWait(ofSeconds(timeOutConfig.getImplicitWait()));
        if (this.platform == Platform.WEB) {
            timeouts.pageLoadTimeout(ofSeconds(timeOutConfig.getPageLoadTimeout()));
            timeouts.scriptTimeout(ofSeconds(timeOutConfig.getScriptTimeout()));
        }
        DriverControl.setWait(new WebDriverWait(driver, ofSeconds(timeOutConfig.getExplicitWait())));
    }

    private Platform getBrowserInConfig() {
        return Platform.valueOf(GetConfig.getConfig("platform"));
    }
}