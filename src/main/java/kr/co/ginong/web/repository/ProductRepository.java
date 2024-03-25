package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.Product;

@Mapper
public interface ProductRepository {

    List<Product> findAll(Long categoryId
                        , String query
                        , int offset
                        , int size);

    Product findById(Long id);
    
    void save(Product product);
    void update(Product product);
    void delete(long id);

    int count(Long categoryId, String query);
    
}
