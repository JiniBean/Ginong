package kr.co.ginong.web.repository.product;

import java.util.List;
import java.util.Map;

import kr.co.ginong.web.entity.product.Stock;
import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;

@Mapper
public interface ProductRepository {

    ProductView findById(Long productId);

    List<ProductView> findAll(int offset, int size);

    List<ProductView> findAll(Long categoryId, String query, int offset, int rows, Integer sortType);

    List<Map<String, Object>> findAllCartItems(List<Long> ids);

    List<ProductView> findAllSeasonOut();

    List<ProductView> findAllSeasonOut(int offset, int size);

    List<ProductView> findAllSeasonOut(Long categoryId, String query, int offset, int size, Integer sortType);

    List<ProductView> findAllCommingSoon();

    List<ProductView> findAllCommingSoon(int offset, int size);

    List<ProductView> findAllCommingSoon(Long categoryId, String query, int offset, int size, Integer sortType);

    List<ProductView> findAllBestProduct();

    List<ProductView> findAllSaleProduct();

    List<ProductView> findAllPickProduct();

    int count(Long categoryId, String query);


    void save(Product product);

    void saveStock(Stock stock);

    void update(Product product);

    void updateState(List<Long> ids);

    void delete(long id);



}
