package kr.co.ginong.web.repository.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.order.OrderView;

@Mapper
public interface OrderViewRepository {
    OrderView findByOrderId(Long memberId);

}