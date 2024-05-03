package kr.co.ginong.web.repository.order;

import kr.co.ginong.web.entity.order.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderRepository {
    List<Order> findAll();

    List<Order> findById(Long id);
    List<Order> findByMemberId(Long memberId);
    List<Order> findCancellationByMemberId(Long memberId);
    
    boolean save(Order order);
    void update(Long orderId, int orderType);
    void delete(long id);

}
