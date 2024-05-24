package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.member.JoinRoute;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.service.statistics.OrderStatisticsService;
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
    private OrderStatisticsService orderService;

    @GetMapping("order")
    public Map<String, Object> orderStatus(){

        return orderService.getStatus();

    }
    @GetMapping("best-seller")
    public List<OrderView> getBestSeller() {
        return orderService.getBestSeller();
    }

    @GetMapping("join-route")
    public List<JoinRoute> getJoinRoute() {
        return orderService.getJoinRoute();
    }
}
