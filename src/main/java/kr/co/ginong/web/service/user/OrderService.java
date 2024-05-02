package kr.co.ginong.web.service.user;

import java.util.List;

import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.entity.order.OrderItemView;
import kr.co.ginong.web.entity.order.OrderView;

public interface OrderService {

    List<Order> get(Long id);
    List<Order> getListByMemberId(Long orderId);
    
    OrderView getOrderInfo(Long orderId);

    List<OrderItem> getItems(Long id);
    List<OrderItemView> getList(Long orderId);


    boolean add(Order order);
    boolean addItems(List<OrderItem> items);



}
