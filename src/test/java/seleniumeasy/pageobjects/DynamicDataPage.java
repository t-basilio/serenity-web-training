package seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementWithText;

@DefaultUrl("http://demo.seleniumeasy.com/dynamic-data-loading-demo.html")
public class DynamicDataPage extends SeleniumEasyForm{

    private static final By USER_DETAILS_PANEL = By.id("loading");

    public void getNewUser() {
        $(FormButton.withLabel("Get New User")).click();
        withTimeoutOf(Duration.ofSeconds(30))
                .waitFor(invisibilityOfElementWithText(USER_DETAILS_PANEL, "loading..."));
    }

    public String userDescription(){
        return $(USER_DETAILS_PANEL).getText();
    }

}
