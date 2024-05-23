package kr.co.ginong.web.repository.product;

import kr.co.ginong.web.entity.product.ProductImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImgRepository {

    List<ProductImg> findAll();
    List<ProductImg> findById(Long id);
    void save(ProductImg productImg);

    int count();
}
