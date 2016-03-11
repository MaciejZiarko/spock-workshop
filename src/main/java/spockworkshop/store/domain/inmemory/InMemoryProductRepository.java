package spockworkshop.store.domain.inmemory;

import spockworkshop.store.domain.Product;
import spockworkshop.store.domain.ProductRepository;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> idToProductMapping = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        checkArgument(product != null, "Product cannot be null");
        idToProductMapping.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findProductById(String id) {
        checkArgument(!isNullOrEmpty(id), "Provided id cannot be null or empty");
        Product foundProduct = idToProductMapping.get(id);
        return Optional.ofNullable(foundProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(idToProductMapping.values());
    }
}
