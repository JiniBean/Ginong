package kr.co.ginong.web.repository.product;

import kr.co.ginong.web.entity.product.Stock;
import kr.co.ginong.web.entity.product.StockView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockRepository {

    List<StockView> findByPrdId(Long productId);
    StockView findById(Long id);
    List<StockView> findAll(String query,Boolean amount, Boolean current);

    Boolean save(Stock stock);

    boolean update(Stock stock);

    Boolean delete(List<Long> ids);
}
