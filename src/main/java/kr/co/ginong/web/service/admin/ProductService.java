package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> getList();

    void save(Product product);

    void update(Product product);

    ProductView get(Long productId);
}
