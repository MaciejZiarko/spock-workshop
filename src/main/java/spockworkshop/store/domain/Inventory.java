package spockworkshop.store.domain;

public interface Inventory {

    int getAvailabilityOfProduct(Product product);

    void increaseProductAvailability(Product product, int increaseAmount);

    void decreaseProductAvailability(Product product, int decreaseAmount) throws ProductNotAvailableException;

    boolean isAvailable(Product product, int requestedAvailability);
}
