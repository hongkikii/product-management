package kr.co.hanbit.product.management.infrastructure;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import kr.co.hanbit.product.management.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ListProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong();

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));
        products.add(product);
        return product;
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Product> findAll() {
        return products;
    }
}
