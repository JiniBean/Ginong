package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;

import java.util.Date;
import java.util.List;

public interface ProductService {
    List<ProductView> getList(int page);

    void save(Product product);//, Date madeDate, String amount);

    void update(Product product);

    ProductView get(Long productId);

    void delete(Long productId);
}
