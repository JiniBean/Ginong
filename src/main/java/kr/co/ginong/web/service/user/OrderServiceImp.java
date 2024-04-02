package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public List<Order> get(Long id) {

        List<Order> list = repository.findById(id);

        return list;
    }

    @Override
    public long addOrder(Order order) {
        repository.save(order);

        return order.getId();

    }
}
