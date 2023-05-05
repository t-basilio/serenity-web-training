
package seleniumeasy;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import seleniumeasy.pageobjects.TwoInputFieldForm;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SerenityParameterizedRunner.class)
@WithTag("local")
//@Concurrent
@UseTestDataFrom("test-data/calculations.csv")
public class CalculationsTest {

    private String a;
    private String b;
    private String total;

    @Managed
    WebDriver driver;

    TwoInputFieldForm twoInputFieldForm;

    @Qualifier
    public String qualifier() { return a + " + " + b + " should equal " + total; }
    @Test
    public void shouldCalculateTheCorrectTotal() {
        twoInputFieldForm.open();

        twoInputFieldForm.enterA(a);
        twoInputFieldForm.enterB(b);
        twoInputFieldForm.getTotal();

        assertThat(twoInputFieldForm.displayedTotal()).isEqualTo(total);
    }
}
