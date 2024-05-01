package kr.co.ginong.web.repository.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.order.OrderCategory;

@Mapper
public interface OrderCategoryRepository {
    List<OrderCategory> findAll();
    
    OrderCategory findById(long id);
    
}
