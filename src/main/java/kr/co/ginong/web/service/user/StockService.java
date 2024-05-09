package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.product.StockView;

import java.util.List;

public interface StockService {

    List<StockView> get(Long productId);
    List<StockView> getList(Boolean amount, Boolean current);

}
