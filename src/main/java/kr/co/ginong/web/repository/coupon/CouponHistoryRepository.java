package kr.co.ginong.web.repository.coupon;

import kr.co.ginong.web.entity.coupon.CouponHistory;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CouponHistoryRepository {
    List<CouponHistoryView> findAll();

    CouponHistoryView findById(Long id);
    
    void save(CouponHistory couponHistory);
    void update(CouponHistory couponHistory);
    void delete(long id);

    int count(Date usedDate);
}
