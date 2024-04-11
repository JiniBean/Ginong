package kr.co.ginong.web.repository.order;

import kr.co.ginong.web.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemRepository {
    List<OrderItem> findAll(Long orderId);

    boolean save(OrderItem item);
}
