package kr.co.ginong.web.repository;

import java.util.List;

import kr.co.ginong.web.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;

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

    //void delete(long id);


}
