package kr.co.ginong.web.service.statistics;

import java.util.List;
import java.util.Map;

import kr.co.ginong.web.entity.member.JoinRoute;
import kr.co.ginong.web.entity.order.OrderView;

public interface StatisticsService {

    Map<String, Object> getOrderStatus();

    List<OrderView> getBestSeller();

    List<JoinRoute> getJoinRoute();

    List<Map<String, Object>> getStockStatus();
}
