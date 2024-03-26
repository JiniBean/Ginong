package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.ProductQna;


@Mapper
public interface ProductQnaRepository {
    List<ProductQna> findAll();

    ProductQna findById(Long id);
    
    void save(ProductQna productQna);
    void update(ProductQna productQna);
    void delete(long id);

    int count(Long categoryId, String query);
}
