package spockworkshop.store.domain;

import org.joda.money.CurrencyUnit;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

public class Shop {

    private final CurrencyUnit supportedCurrency;
    private final ProductRepository productRepository;
    private final Inventory inventory;

    public Shop(CurrencyUnit supportedCurrency, ProductRepository productRepository, Inventory inventory) {
        this.supportedCurrency = supportedCurrency;
        this.productRepository = productRepository;
        this.inventory = inventory;
    }

    public Basket startShopping() {
        return new Basket(supportedCurrency);
    }

    public Basket addToBasket(Basket basket, String productId, int numberOfItems)
                                                        throws ProductNotFoundException, ProductNotAvailableException {
        checkArgument(basket != null, "Basket must be provided!");
        checkArgument(!isNullOrEmpty(productId), "Product id cannot be empty");
        checkArgument(numberOfItems > 0, "Number of items must be bigger than 0");
        Optional<Product> maybeProduct = productRepository.findProductById(productId);
        if (!maybeProduct.isPresent()) {
            throw new ProductNotFoundException("Product is not offered by the shop!");
        }
        Product product = maybeProduct.get();
        inventory.decreaseProductAvailability(product, numberOfItems);
        basket.addPosition(new BasketPosition(numberOfItems, product));
        return basket;
    }

}
