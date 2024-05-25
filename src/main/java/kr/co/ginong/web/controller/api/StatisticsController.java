package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.member.JoinRouteStatsView;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.service.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/stats")
public class StatisticsController {

    @Autowired
    private StatisticsService service;

    @GetMapping("order")
    public Map<String, Object> orderStats(){

        return service.getOrderStatus();

    }

    @GetMapping("stock")
    public List<Map<String, Object>> stockStats(){

        return service.getStockStatus();

    }

    @GetMapping("inquiry")
    public List<Map<String, Object>> inquiryStats(){

        return service.getInquiryStatus();

    }

    @GetMapping("best-seller")
    public List<OrderView> countBestSeller() {
        return service.getBestSeller();
    }

    @GetMapping("join-route")
    public List<JoinRouteStatsView> countJoinRoute() {
        return service.getJoinRoute();
    }

    @GetMapping("calculate-sales")
    public List<OrderView> countSales() {
        return service.getResultOfSales();
    }


}
