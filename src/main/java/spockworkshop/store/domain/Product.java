package spockworkshop.store.domain;

import org.joda.money.Money;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

public class Product {

    private final String id;
    private final String name;
    private final Money itemPrice;

    public Product(String id, String name, Money itemPrice) {
        checkArgument(!isNullOrEmpty(id), "Product id '" + id + "' is invalid");
        checkArgument(!isNullOrEmpty(name), "Name must be provided");
        checkArgument(itemPrice != null, "Price must be provided");
        this.id = id;
        this.name = name;
        this.itemPrice = itemPrice;
    }

    public Product changePrice(Money newPrice) {
        checkArgument(newPrice != null, "New price is required");
        return new Product(id, name, newPrice);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
