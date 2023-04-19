package seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://the-internet.herokuapp.com/hovers")
public class HoverPage extends PageObject {
    public static final String FIGURE = "(//*[@class='figure'])[{0}]";
    public static final String FIGURE_CAPTION = "(//*[@class='figcaption'])[{0}]";

    public void hoverOverFigure(int number) {
        WebElementFacade figure = $(FIGURE, number);

        //withAction().dragAndDrop($(FIGURE,1), $(FIGURE,2)).perform();
        withAction().moveToElement(figure)
                .perform();
    }

    public WebElementState captionForFigure(int number) {
        return $(FIGURE_CAPTION, number);
    }
}
