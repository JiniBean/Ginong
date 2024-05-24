package kr.co.ginong.web.repository.product;

import kr.co.ginong.web.entity.product.Stock;
import kr.co.ginong.web.entity.product.StockView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StockRepository {

    List<StockView> findByPrdId(Long productId);
    StockView findById(Long id);
    List<StockView> findAll(String query,Boolean amount, Boolean current);

    boolean save(Stock stock);
    boolean saveByOrder(List<Stock> list);

    boolean update(Stock stock);

    Boolean delete(List<Long> ids);

    //    ----------- 통계 관련 메소드 -------------------
    List<Map<String, Object>> countStatus();

}
