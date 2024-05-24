package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.member.JoinRoute;
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
    public Map<String, Object> orderStatus(){

        return service.getOrderStatus();

    }

    @GetMapping("stock")
    public List<Map<String, Object>> stockStatus(){

        System.out.println("=======================");
        System.out.println(service.getStockStatus());
        System.out.println("=======================");
        return service.getStockStatus();

    }

    @GetMapping("best-seller")
    public List<OrderView> getBestSeller() {
        return service.getBestSeller();
    }

    @GetMapping("join-route")
    public List<JoinRoute> getJoinRoute() {
        return service.getJoinRoute();
    }




}
