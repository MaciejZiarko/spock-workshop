package spockworkshop.store.domain.inmemory;

import spockworkshop.store.domain.Inventory;
import spockworkshop.store.domain.Product;
import spockworkshop.store.domain.ProductNotAvailableException;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class InMemoryInventory implements Inventory {

    private final Map<String, Integer> storage = new HashMap<>();

    @Override
    public int getAvailabilityOfProduct(Product product) {
        checkArgument(product != null, "Product must be specified");
        String productId = product.getId();
        makeSureProductIsTracked(productId);
        return storage.get(productId);
    }

    @Override
    public void decreaseProductAvailability(Product product, int decreaseAmount) throws ProductNotAvailableException {
        checkArgument(product != null, "Product must be specified");
        checkArgument(decreaseAmount > 0, "Decrease amount must be a positive integer");
        changeAvailability(product, -decreaseAmount);
    }

    @Override
    public void increaseProductAvailability(Product product, int increaseAmount) {
        checkArgument(product != null, "Product must be specified");
        checkArgument(increaseAmount > 0, "Increase amount must be a positive integer");
        try {
            changeAvailability(product, increaseAmount);
        } catch (ProductNotAvailableException e) {
            throw new IllegalStateException("Product not available should never happen during product availability increase!");
        }
    }

    @Override
    public boolean isAvailable(Product product, int requestedAvailability) {
        return getAvailabilityOfProduct(product) >= requestedAvailability;
    }

    private void makeSureProductIsTracked(String productId) {
        if (!storage.containsKey(productId)) {
            storage.put(productId, 0);
        }
    }

    private void changeAvailability(Product product, int change) throws ProductNotAvailableException {
        String productId = product.getId();
        makeSureProductIsTracked(productId);
        Integer availability = storage.get(productId);
        int newAvailability = availability + change;
        if (newAvailability < 0) {
            throw new ProductNotAvailableException(product.getId(), change);
        }
        storage.put(productId, newAvailability);
    }
}
