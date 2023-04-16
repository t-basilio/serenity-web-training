package serenityswag.cart;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.LoginActions;
import serenityswag.inventory.ProductList;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

@RunWith(SerenityRunner.class)
public class WhenAddingAnItemToTheCart {

    @Managed(driver = "microsoftedge", options = "--remote-allow-origins=*")
    WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    CartActions cart;

    ProductList productList;

    ShoppingCartIcon shoppingCartIcon;

    CartPage cartPage;

    @Before
    public void login() {
        login.as(STANDARD_USER);
    }

    @Test
    public void theCorrectItemCountShouldBeShow() {
        // Check that the shopping cart badge is empty
        Serenity.reportThat("The shopping cart badge should be empty",
                () -> assertThat(shoppingCartIcon.badgeCount()).isEmpty()
        );


        // Add 1 to the cart
        cart.addItem("Sauce Labs Backpack");

        // The shopping cart badge should be "1"
        Serenity.reportThat("The shopping cart badge should be now '1'",
                () -> assertThat(shoppingCartIcon.badgeCount()).isEqualTo("1")
        );


    }

    @Test
    public void allTheItemsShouldAppearInTheCart() {
        // Add several items to the cart
        List<String> selectedItems = firstThreeProductTitlesDisplayed();

        // Open the cart page
        cart.addItems(selectedItems);

        String cartBadgeCount = Integer.toString(selectedItems.size());
        Serenity.reportThat("The shopping cart badge should be now " + cartBadgeCount,
                () -> assertThat(shoppingCartIcon.badgeCount()).isEqualTo(cartBadgeCount)
        );

        cart.openCart();

        Serenity.reportThat("Should see all the items listed",
                () -> assertThat(cart.displayedItems()).containsExactlyElementsOf(selectedItems)
         );

    }

    @Test
    public void pricesForEachItemShouldBeShowInTheCart() {
        // Add items to the shopping cart
        List<String> selectedItems = firstThreeProductTitlesDisplayed();
        cart.addItems(selectedItems);

        // Open the cart page
        cartPage.open();

        // Check that each item in the cart has a price
        List<CartItem> items = cartPage.items();

        Serenity.reportThat("Check that each item in the cart has a price",
                () -> assertThat(items).hasSize(3)
                        .allMatch(item -> item.getPrice() > 0.0)
        );

    }

    private List<String> firstThreeProductTitlesDisplayed() {
        return productList.titles().subList(0, 3);
    }
}
