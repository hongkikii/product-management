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

    public List<Product> findByNameContaining(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    /**
     * 스레드 세이프하지 않음
     * 만약 1과 2 사이에 product가 추가되거나 삭제된다면?
     * 엉뚱한 product가 수정될 것.
     *
     * CopyOnwriteArrayList를 사용해서 개별 작업에 대해서는 스레드 안전하지만,
     * 여러 작업을 조합하여 수행하는 경우에는 원자적으로 처리되지 않기 때문.
     *
     * 대안
     * 1. 추가적인 동기화 작업 수행하기
     * 2. Product에 setter를 추가, product 조회 후 setter로 값을 수정하기
     * 3. 해당 Product를 remove하여 삭제한 후 index와 상관 없이 수정 후 가장 뒤에 추가해주기
     */
    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product); // 1
        products.set(indexToModify, product); // 2
        return product;
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
