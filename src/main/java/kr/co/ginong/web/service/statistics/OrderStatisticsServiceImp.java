package kr.co.ginong.web.service.statistics;

import kr.co.ginong.web.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderStatisticsServiceImp implements OrderStatisticsService{

    @Autowired
    private OrderRepository repository;


    @Override
    public Map<String, Object> getStatus() {
        return repository.countStatus();
    }
}
