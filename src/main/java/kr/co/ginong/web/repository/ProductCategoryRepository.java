package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.product.ProductCategory;

@Mapper
public interface ProductCategoryRepository {
    List<ProductCategory> findAll();
    ProductCategory findById(long id);
    
    void save(ProductCategory category);
    void update(ProductCategory category);
    void delete(long id);
}
