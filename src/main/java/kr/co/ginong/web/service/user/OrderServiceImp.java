package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.entity.order.OrderItemView;
import kr.co.ginong.web.repository.order.OrderItemRepository;
import kr.co.ginong.web.repository.order.OrderRepository;
import kr.co.ginong.web.repository.order.OrderItemViewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private OrderItemViewRepository viewRepository;

    @Override
    public List<Order> get(Long id) {

        List<Order> list = repository.findById(id);

        return list;
    }

    @Override
    public List<OrderItem> getItems(Long id) {

        List<OrderItem> list = itemRepository.findAll(id);

        return list;
    }

    @Override
    public List<OrderItemView> getList(Long orderId) {
        List<OrderItemView> list = viewRepository.findByOrderId(orderId);
        return list;
    }

    @Override
    public boolean add(Order order) {
       boolean save = repository.save(order);

        return save;

    }


    @Override
    public boolean addItems(List<OrderItem> items) {
        boolean save = itemRepository.save(items);
        return save;
    }
}
