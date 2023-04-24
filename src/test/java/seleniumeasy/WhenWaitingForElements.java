package seleniumeasy;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import seleniumeasy.pageobjects.AlertMessagePage;
import seleniumeasy.pageobjects.DynamicDataPage;
import seleniumeasy.pageobjects.ModalDialogPage;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenWaitingForElements {

    @Managed(driver = "microsoftedge", options = "--remote-allow-origins=*")
    WebDriver driver;

    ModalDialogPage modalDialogPage;
    @Test
    public void waitingForAModalDialog() {
        modalDialogPage.open();

        modalDialogPage.saveChangesButton().shouldNotBeVisible();

        modalDialogPage.openModal();

        modalDialogPage.saveChangesButton().shouldBeVisible();

        modalDialogPage.saveChanges();

        modalDialogPage.saveChangesButton().shouldNotBeVisible();
    }

    AlertMessagePage alertMessagePage;
    @Test
    public void waitingForAMessageToClose() {
        alertMessagePage.open();

        alertMessagePage.openSuccessMessage();

        assertThat(alertMessagePage.alertSuccessMessageText()).contains("I'm an autocloseable success");

        alertMessagePage.waitForMessageDisappear();

        alertMessagePage.alertSuccessMessage().shouldNotBeVisible();
    }

    DynamicDataPage dynamicDataPage;
    @Test
    public void waitingForElementsToAppear() {
        dynamicDataPage.open();

        dynamicDataPage.getNewUser();

        assertThat(dynamicDataPage.userDescription())
                .contains("First Name")
                .contains("Last Name");
    }
}
