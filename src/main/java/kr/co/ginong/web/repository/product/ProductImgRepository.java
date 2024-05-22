package kr.co.ginong.web.repository.product;

import kr.co.ginong.web.entity.product.ProductImg;
import kr.co.ginong.web.entity.product.Stock;
import kr.co.ginong.web.entity.product.StockView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImgRepository {

    List<ProductImg> findAll();
    List<ProductImg> findById(Long id);
    
    int count();
}
