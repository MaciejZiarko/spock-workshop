package spockworkshop.store.domain;

public class ProductNotAvailableException extends Exception {

    private final String productId;
    private final int requestedNumberOfItems;

    public ProductNotAvailableException(String productId, int requestedNumberOfItems) {
        super("Product " + productId + " is not available!");
        this.productId = productId;
        this.requestedNumberOfItems = requestedNumberOfItems;
    }

    public String getProductId() {
        return productId;
    }

    public int getRequestedNumberOfItems() {
        return requestedNumberOfItems;
    }
}
