package seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://demo.seleniumeasy.com/bootstrap-modal-demo.html")
public class ModalDialogPage extends SeleniumEasyForm {

    private static final By LAUNCH_MODAL_BUTTON = By.linkText("Launch modal");
    private static final By SAVE_CHANGES_BUTTON = By.linkText("Save changes");

    public void openModal() {
        find(LAUNCH_MODAL_BUTTON).click();
    }

    public WebElementState saveChangesButton() {
        return find(SAVE_CHANGES_BUTTON);
    }

    public void saveChanges() {
        find(SAVE_CHANGES_BUTTON).click();
    }
}
