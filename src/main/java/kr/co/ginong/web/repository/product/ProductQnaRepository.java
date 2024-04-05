package kr.co.ginong.web.repository.product;

import java.util.List;

import kr.co.ginong.web.entity.product.ProductQnaView;
import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.product.ProductQna;


@Mapper
public interface ProductQnaRepository {
    List<ProductQnaView> findAll(Long productId);
    List<ProductQnaView> findByProductId(Long productId);

    ProductQna findById(Long id);
    
    void save(ProductQna productQna);
    void update(ProductQna productQna);
    void delete(long id);

    int count(Long categoryId, String query);
}
