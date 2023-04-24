package seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.time.Duration;

@DefaultUrl("http://demo.seleniumeasy.com/bootstrap-alert-messages-demo.html")
public class AlertMessagePage extends SeleniumEasyForm {

    private final static By ALERT_SUCCESS_MESSAGE = By.cssSelector(".alert-autocloseable-success");
    public void openSuccessMessage() {
        $("//button[contains(.,'Autocloseable success')]").click();
    }

    public String alertSuccessMessageText() {
        return $(ALERT_SUCCESS_MESSAGE).getText();
    }

    public void waitForMessageDisappear() {
        withTimeoutOf(Duration.ofSeconds(10)).waitForElementsToDisappear(ALERT_SUCCESS_MESSAGE);
    }

    public WebElementState alertSuccessMessage() {
        return $(ALERT_SUCCESS_MESSAGE);
    }
}
