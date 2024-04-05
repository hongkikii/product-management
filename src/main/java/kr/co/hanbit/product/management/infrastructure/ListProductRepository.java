package kr.co.hanbit.product.management.infrastructure;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kr.co.hanbit.product.management.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ListProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();

    public Product add(Product product) {
        products.add(product);
        return product;
    }
}
