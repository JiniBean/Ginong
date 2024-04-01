package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.order.Order;

import java.util.List;

public interface OrderService {

    List<Order> get(Long id);


}
