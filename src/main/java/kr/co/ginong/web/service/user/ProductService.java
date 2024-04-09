package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;

import java.util.List;

public interface ProductService {

    List<ProductView> getList(Integer page);
    List<ProductView> getList(Integer page, Integer sortType);
    List<ProductView> getList(Integer page, String query);
    List<ProductView> getList(Integer page, Long categoryId);
    List<ProductView> getList(Integer page, String query, Integer sortType);
    List<ProductView> getList(Integer page, Long categoryId, String query);
    List<ProductView> getList(Integer page, Long categoryId, Integer sortType);
    List<ProductView> getList(Integer page, String query, Long categoryId, Integer sortType);

    int count();
    int count(Long categoryId);
    int count(String query);
    int count(Long categoryId, String query);

    ProductView get(Long productId);

}
