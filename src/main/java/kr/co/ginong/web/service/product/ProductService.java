package kr.co.ginong.web.service.product;

import kr.co.ginong.web.entity.product.ProductImg;
import kr.co.ginong.web.entity.product.ProductView;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductView get(Long productId);

    Map<String, Object> getBestProductList();

    List<ProductView> getSaleProductList();

    List<ProductView> getPickProductList();

//    List<ProductView> getList(Integer page, Long categoryId, String query, Integer sortType);

    List<ProductView> getList(Integer page, Long categoryId, String query, Integer sortType, Integer rows);

    List<Map<String, Object>> getCartList(List<Long> ids);

    List<ProductView> getSeasonOutList();

    List<ProductView> getSeasonOutList(Integer page, Long categoryId, String query, Integer sortType);

    List<ProductView> getCommingSoonList();

    List<ProductView> getCommingSoonList(Integer page, Long categoryId, String query, Integer sortType);

    int count(Long categoryId, String query);

    List<ProductImg> getImgs(Long id);

}
