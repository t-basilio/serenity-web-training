package seleniumeasy;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import seleniumeasy.actions.FormPage;
import seleniumeasy.pageobjects.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a series of exercises designed to explore how to use
 * Serenity BDD to test various kinds of HTML elements
 */
@RunWith(SerenityRunner.class)
public class WhenInteractingWithInputForms {

    @Managed(driver = "microsoftedge", options = "--remote-allow-origins=*", uniqueSession = true)
    WebDriver webDriver;

    @Steps
    NavigateActions navigate;
    /**

     * Basic form fields:
     * Enter a message and check that the message is correctly displayed
     * http://demo.seleniumeasy.com/basic-first-form-demo.html
     */

    SingleInputFieldForm singleInputFieldForm;
    @Test
    public void basicForms() {
        navigate.to(FormPage.SingleInputFieldForm);

        singleInputFieldForm.enterMessage("Hi there");

        singleInputFieldForm.showMessage();

        assertThat(singleInputFieldForm.displayedMessage()).isEqualTo("Hi there");

    }

    /**
     * Basic form fields (part 2)
     * Enter two values and calculate the result
     * http://demo.seleniumeasy.com/basic-first-form-demo.html
     */

    TwoInputFieldForm twoInputFiledForm;
    @Test
    public void basicFormsWithMultipleFields() {
        navigate.to(FormPage.TwoInputFiledForm);

        twoInputFiledForm.enterA("2");
        twoInputFiledForm.enterB("3");

        twoInputFiledForm.getTotal();

        assertThat(twoInputFiledForm.displayedTotal()).isEqualTo("5");
    }

    /**
     * Checkboxes
     * Check that a message appears when you click the checkbox
     * http://demo.seleniumeasy.com/basic-checkbox-demo.html
     */

    CheckBoxForm checkBoxForm;
    @Test
    public void singleCheckbox() {
        navigate.to(FormPage.CheckBoxForm);

        checkBoxForm.setAgeSelected();

        assertThat(checkBoxForm.ageText()).isEqualTo("Success - Check box is checked");
    }

    private static List<String> ALL_THE_OPTIONS = Arrays
            .asList("Option 1", "Option 2", "Option 3", "Option 4");
    @Test
    public void multipleCheckboxes() {

        checkBoxForm.open();

        assertThat(ALL_THE_OPTIONS).allMatch(
                option -> !checkBoxForm.optionIsCheckedFor(option)
        );

        checkBoxForm.checkAll();

        assertThat(ALL_THE_OPTIONS).allMatch(
                option -> checkBoxForm.optionIsCheckedFor(option)
        );
    }

    /**
     * Radio buttons
     * Check that a message appears when you click the radio button
     * http://demo.seleniumeasy.com/basic-radiobutton-demo.html
     */

    RadionButtonForm radionButtonForm;
    @Test
    public void radioButtons() {
        radionButtonForm.open();

        radionButtonForm.selectOption("Male");

        radionButtonForm.getCheckedValue();

        assertThat(radionButtonForm.getResult()).isEqualTo("Radio button 'Male' is checked");
    }

    MultipleRadioButtonForm multipleRadioButtonForm;
    @Test
    public void multipleRadioButtons() {
        multipleRadioButtonForm.open();

        multipleRadioButtonForm.selectGender("Female");
        multipleRadioButtonForm.selectAgeGroup("15 - 50");
        multipleRadioButtonForm.getValues();

        assertThat(multipleRadioButtonForm.getResult())
                .contains("Sex : Female")
                .contains("Age group: 15 - 50");
    }

    /**
     * Dropdown lists
     * http://demo.seleniumeasy.com/basic-select-dropdown-demo.html
     */
    SelectListForm selectListForm;
    @Test
    public void selectList() {
        selectListForm.open();
        assertThat(selectListForm.selectedDay()).isEmpty();

        selectListForm.selectDay("Monday");

        assertThat(selectListForm.getSelectedDay()).isEqualTo("Day selected :- Monday");
    }

    /**
     * Multi-Select Dropdown lists
     * http://demo.seleniumeasy.com/basic-select-dropdown-demo.html
     *
     */

    MultiSelectListForm multiSelectListForm;
    @Test
    public void multiSelectList() {
        multiSelectListForm.open();

        assertThat(multiSelectListForm.selectedStates()).isEmpty();

        multiSelectListForm.selectStates("Florida", "Ohio", "Texas");
        assertThat(multiSelectListForm.selectedStates()).containsExactly("Florida", "Ohio", "Texas");

        //multiSelectListForm.pressGetAllSelected();
        //assertThat(multiSelectListForm.getAllSelected()).isEqualTo("Options selected are : Florida,Ohio,Texas");
    }

    HoverPage hoverPage;
    @Test
    public void hover() {
        hoverPage.open();

        hoverPage.hoverOverFigure(1);
        hoverPage.captionForFigure(1).shouldBeVisible();
        hoverPage.captionForFigure(1).shouldContainText("user1");

        hoverPage.hoverOverFigure(2);
        hoverPage.captionForFigure(2).shouldBeVisible();
        hoverPage.captionForFigure(2).shouldContainText("user2");
    }

}
