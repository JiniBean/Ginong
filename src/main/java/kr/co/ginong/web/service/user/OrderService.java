package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.entity.order.OrderView;

import java.util.List;

public interface OrderService {

    List<Order> get(Long id);
    List<OrderItem> getItems(Long id);
    List<OrderView> getList(Long memberId);


    boolean add(Order order);
    boolean addItems(List<OrderItem> items);



}
