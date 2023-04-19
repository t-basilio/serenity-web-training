package seleniumeasy.pageobjects;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import seleniumeasy.actions.FormPage;

public class NavigateActions extends UIInteractionSteps {

    @Step
    public void to(FormPage formPage) {
        openPageNamed(formPage.name());
    }
}
