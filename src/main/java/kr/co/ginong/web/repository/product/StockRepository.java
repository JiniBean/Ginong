package kr.co.ginong.web.repository.product;

import kr.co.ginong.web.entity.product.StockView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockRepository {

    List<StockView> findById(Long productId);
    List<StockView> findAll(String query,Boolean amount, Boolean current);

}
