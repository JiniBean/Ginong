package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.product.StockView;

import java.util.List;

public interface StockService {

    StockView get(Long productId);
    List<StockView> getList();

}
