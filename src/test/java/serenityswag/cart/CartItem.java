package serenityswag.cart;

public class CartItem {

    private String title;
    private String description;
    private Double price;
    CartItem(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
