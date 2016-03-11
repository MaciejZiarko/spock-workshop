package spockworkshop.store.domain;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import static com.google.common.base.Preconditions.checkArgument;

public class BasketPosition {

    private final int numberOfItems;
    private final Product product;

    public BasketPosition(int numberOfItems, Product product) {
        checkArgument(numberOfItems > 0, "Number of item must be bigger than 0");
        checkArgument(product != null, "Product must be provided");
        this.numberOfItems = numberOfItems;
        this.product = product;
    }
    
    public Money getTotalPriceForBasketPosition() {
        Money singleItemPrice = product.getItemPrice();
        return singleItemPrice.multipliedBy(numberOfItems);
    }

    public CurrencyUnit getCurrency() {
        return product.getItemPrice().getCurrencyUnit();
    }

    @Override
    public String toString() {
        return "BasketPosition{" +
                "numberOfItems=" + numberOfItems +
                ", product=" + product +
                '}';
    }
}
