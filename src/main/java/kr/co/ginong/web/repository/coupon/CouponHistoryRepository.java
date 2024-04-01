package kr.co.ginong.web.repository.coupon;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.coupon.CouponHistory;
import kr.co.ginong.web.entity.order.Location;

@Mapper
public interface CouponHistoryRepository {
    List<CouponHistory> findAll();

    CouponHistory findById(Long id);
    
    void save(Location location);
    void update(Location location);
    void delete(long id);

    int count(Long categoryId, String query);
}
