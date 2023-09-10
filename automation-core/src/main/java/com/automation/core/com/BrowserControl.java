package com.automation.core.com;

import com.automation.core.config.WebConfig;
import com.automation.core.interfaces.ibrowser.IBrowser;
import com.automation.core.utils.ErrorHandler;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.automation.core.com.Navigate.navigate;
import static com.automation.core.enums.Message.*;

import static com.automation.core.utils.ErrorHandler.requireNotNull;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

public class BrowserControl implements IBrowser {
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    private static final Logger LOGGER = getLogger();

    public static <D extends WebDriver> D getDriver() {
        return (D) threadLocal.get();
    }

    @Override
    public void selectBrowser() {
        LOGGER.traceEntry();
        final var webConfig = WebConfig.initInstance();

        switch (requireNotNull(webConfig.getBrowser(), EMPTY_BROWSER)) {
            case CHROME -> {
                RemoteWebDriver webDriver = setupChromeDriver(webConfig);
                setDriver(webDriver);
            }
            case NONE -> ErrorHandler.throwError(INVALID_BROWSER);

            case FIREFOX, IE, EDGE, SAFARI -> LOGGER.info("Not implemented yet");

            default -> setDriver(setupChromeDriver(webConfig));

        }
        setDriverSize(webConfig);
        navigateToUrl(webConfig.getBaseUrl());
        LOGGER.traceExit();
    }

    private <T extends WebDriver> void setDriver(final T driver) {
        threadLocal.set(driver);
    }

    private RemoteWebDriver setupChromeDriver(final WebConfig webConfig) {
        LOGGER.traceEntry();
        WebDriverManager.chromedriver().setup();
        final var options = getChromeOptions(webConfig);
        return LOGGER.traceExit(new ChromeDriver(options));
    }

    private ChromeOptions getChromeOptions(final WebConfig webConfig) {
        LOGGER.traceEntry();
        final var options = new ChromeOptions();
        setCommonOptions(options, webConfig);
        return LOGGER.traceExit(options);
    }

    private <T extends ChromiumOptions> void setCommonOptions(final T options, final WebConfig webConfig) {
        ofNullable(webConfig.getPlatform()).ifPresent(options::setPlatformName);
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        ofNullable(webConfig.getBrowserOptions()).ifPresent(l -> l.forEach(options::addArguments));
        if (webConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }
    }

    private void setDriverSize(final WebConfig webConfig) {
        final var window = threadLocal.get().manage().window();
        switch (webConfig.getWindowSize()) {
            case FULL_SCREEN -> window.fullscreen();
            case CUSTOM -> window.setSize(webConfig.getSize());
            default -> ErrorHandler.throwError(EXCEPTION_ERROR, "Invalid window size");
        }
    }

    private void navigateToUrl(final String baseUrl) {
        navigate().to(baseUrl);
    }
}
