package kr.co.ginong.web.service.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.entity.order.OrderItemView;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.repository.order.OrderItemRepository;
import kr.co.ginong.web.repository.order.OrderItemViewRepository;
import kr.co.ginong.web.repository.order.OrderRepository;
import kr.co.ginong.web.repository.order.OrderViewRepository;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderViewRepository viewRepository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private OrderItemViewRepository itemViewRepository;

    @Override
    public List<Order> get(Long id) {

        List<Order> list = repository.findById(id);

        return list;
    }
    

    @Override
    public OrderView getOrderInfo(Long orderId) {
        return viewRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderItem> getItems(Long id) {

        List<OrderItem> list = itemRepository.findAll(id);

        return list;
    }

    @Override
    public List<OrderItemView> getList(Long orderId) {
        List<OrderItemView> list = itemViewRepository.findByOrderId(orderId);
        return list;
    }


    @Override
    public List<Order> getCanceledListByMemberId(Long memberId) {
        return repository.findCancellationByMemberId(memberId);
    }


    @Override
    public List<Order> getListByMemberId(Long memberId) {
        return repository.findByMemberId(memberId);
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

    @Override
    public void updateOrderType(Long orderId, int orderType) {
        repository.update(orderId, orderType);
    }

    

    
}
