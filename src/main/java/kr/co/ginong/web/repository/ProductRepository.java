package kr.co.ginong.web.repository;

import java.util.List;

import kr.co.ginong.web.entity.product.Stock;
import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;

@Mapper
public interface ProductRepository {

    List<ProductView> findAll(Long categoryId
                        , String query
                        , int offset
                        , int size
                        , Integer sortType);

    int count(Long categoryId, String query);


    void save(Product product);
    void saveStock(Stock stock);
    void update(Product product);
    ProductView findById(Long productId);

    List<ProductView> findAll(int offset, int size);


    //void delete(long id);


}
