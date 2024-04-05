package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.domain.Product;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        // Product 생성하고 리스트에 넣기
        return product;
    }
}
