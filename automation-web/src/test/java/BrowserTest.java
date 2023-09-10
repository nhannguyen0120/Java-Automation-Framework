import com.automation.core.com.DriverControl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static automation.pageObject.BrowserPage.*;
import static com.automation.core.action.ClickAction.*;
import static com.automation.core.action.TextBoxActions.onTextBox;

public final class BrowserTest {
    @BeforeTest
    public void setUp() {
        DriverControl.getInstance().setUp();
    }

    @AfterTest()
    public void tearDown() {
        DriverControl.getInstance().tearDown();
    }

    @Test
    public void test() {

        System.out.println(browserPage().historyText2("A"));

        System.out.println(browserPage().getHistoryText().getName());
        System.out.println(browserPage().getSelectLabel().getName());
        System.out.println(browserPage().getSearchText().getName());

        withMouse(browserPage().getSelectLabel()).hover();
        withMouse(browserPage().getSelectLabel()).click();
        onTextBox(browserPage().getSearchText()).enterText("Hello");
        if (onElement(browserPage().getHistoryText()).isDisplayed()) {
            System.out.println(onElement(browserPage().getHistoryText()).getText());
        }
    }
}
