package automation.pageObject;

import com.automation.core.com.Locator;
import lombok.Getter;

import static java.lang.String.format;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

@Getter
public class BrowserPage {

    private static final BrowserPage BROWSER_PAGE = new BrowserPage();

    public static BrowserPage browserPage() {
        return BROWSER_PAGE;
    }

    private final Locator selectLabel = Locator.buildLocator()
            .name("selectLabel")
            .web(xpath("//span[text() = 'Test History']"))
            .build();

    private final Locator searchText = Locator.buildLocator()
            .name("searchText")
            .web(id("filter"))
            .build();

    private final Locator historyText = Locator.buildLocator()
            .name("historyText")
            .web(xpath("//div[@class='logged-out-history']//p"))
            .build();

    public Locator historyText2(String name) {
        return Locator.buildLocator()
                .name(name)
                .web(xpath(format("//div[@class='%s']//p", name))) // [.android]-[.ios] implement later
                .build();
    }

    private final Locator invalidXpath = Locator.buildLocator()
            .name("invalid")
            .web(xpath("//a[text() = 'invalid']]"))
            .build();
}
