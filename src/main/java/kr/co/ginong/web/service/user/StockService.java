package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.product.Stock;
import kr.co.ginong.web.entity.product.StockCategory;
import kr.co.ginong.web.entity.product.StockView;

import java.util.List;

public interface StockService {

    StockView getById(Long id);
    List<StockView> getByPrdId(Long productId);
    List<StockView> getList(String query,Boolean amount, Boolean current);

    List<StockCategory> getCategories();

    Boolean add(Stock stock);

    boolean edit(Stock stock);

    Boolean delete(List<Long> ids);
}
