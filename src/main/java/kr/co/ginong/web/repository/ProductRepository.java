package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;

@Mapper
public interface ProductRepository {

    List<ProductView> findAll(Long categoryId
                        , String query
                        , int offset
                        , int size);

    Product findById(Long id);
    int count(Long categoryId, String query);


    void save(Product product);
    void update(Product product);

    //void delete(long id);


}
