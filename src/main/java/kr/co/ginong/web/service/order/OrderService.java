package kr.co.ginong.web.service.order;

import java.util.List;

import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderCategory;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.entity.order.OrderView;

public interface OrderService {

    List<OrderView> getList(Long memberId,String query, Boolean sort);


    List<OrderView> getCancelList(Long memberId,String query);

    List<OrderView> getExchangeList(Long memberId, String query, Integer sort);
    List<OrderView> getRefundList(Long memberId, String query, Boolean sort);

    List<OrderView> getItems(Long orderId);

    List<OrderCategory> getCategories();

    boolean add(Order order);
    boolean addItems(List<OrderItem> items);


    Boolean edit(Order order);

    Integer getCountOrder(Long memberId);

    Order getRecentOrder(Long memberId);

}
