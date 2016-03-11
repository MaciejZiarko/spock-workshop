package spockworkshop.store.domain;

public class ProductNotFoundException extends Exception {

    private final String productId;

    public ProductNotFoundException(String productId) {
        super("Product " + productId + " is not found!");
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
