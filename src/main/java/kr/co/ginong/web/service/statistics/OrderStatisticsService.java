package kr.co.ginong.web.service.statistics;

import java.util.List;
import java.util.Map;

import kr.co.ginong.web.entity.order.OrderView;

public interface OrderStatisticsService {

    Map<String, Object> getStatus();

    List<OrderView> getBestSeller();

}
