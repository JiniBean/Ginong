package kr.co.ginong.web.repository.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.order.OrderItemView;

@Mapper
public interface OrderItemViewRepository {
    List<OrderItemView> findByOrderId(Long memberId);

}
