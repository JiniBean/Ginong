package kr.co.ginong.web.service.user;

import kr.co.ginong.web.dto.Pager;
import kr.co.ginong.web.dto.ProductFilter;
import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;

import java.util.List;

public interface ProductService {
    ProductView get(Long productId);

    List<ProductView> getBestProductList();

    List<ProductView> getSaleProductList();

    List<ProductView> getPickProductList();

//    List<ProductView> getList(Integer page, Long categoryId, String query, Integer sortType);

    List<ProductView> getList(Integer page, Long categoryId, String query, Integer sortType, Integer rows);

    List<ProductView> getSeasonOutList();

    List<ProductView> getSeasonOutList(Integer page, Long categoryId, String query, Integer sortType);

    List<ProductView> getCommingSoonList();

    List<ProductView> getCommingSoonList(Integer page, Long categoryId, String query, Integer sortType);

    int count(Long categoryId, String query);

}
