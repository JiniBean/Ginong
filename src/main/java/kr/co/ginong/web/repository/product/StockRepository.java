package kr.co.ginong.web.repository.product;

import kr.co.ginong.web.entity.product.StockView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockRepository {

    StockView findById(Long productId);
    List<StockView> findAll();

}
