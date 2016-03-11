package spockworkshop.store.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void addProduct(Product product);

    Optional<Product> findProductById(String id);

    List<Product> getAllProducts();

}
