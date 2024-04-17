package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;

import java.util.Date;
import java.util.List;

public interface ProductService {
    List<ProductView> getList(int page, String query);
    List<ProductView> getList(int page);

    int getCount(String query);
    int getCount();

    void save(Product product);

    void update(Product product);

    void hidden(List<Long> ids);
    ProductView get(Long productId);

    void delete(Long productId);
}
