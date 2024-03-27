package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;

import java.util.List;

public interface ProductService {

    List<ProductView> getList(Integer page);
    List<ProductView> getList(Integer page, String query);
    List<ProductView> getList(Integer page, Long categoryId);
    List<ProductView> getList(Integer page, Long categoryId, String query);

    int count();
    int count(Long categoryId);
    int count(String query);
    int count(Long categoryId, String query);

    Product get(Long productId);
}
