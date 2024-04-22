package kr.co.ginong.web.service.user;

import kr.co.ginong.web.dto.Pager;
import kr.co.ginong.web.dto.ProductFilter;
import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> getList(Integer page, Long categoryId, String query, Integer sortType);
    int count(Long categoryId, String query);

    ProductView get(Long productId);

}
