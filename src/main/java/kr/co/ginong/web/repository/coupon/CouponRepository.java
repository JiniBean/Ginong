package kr.co.ginong.web.repository.coupon;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.notice.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponRepository {
    List<Coupon> findAll();
    Coupon findById(Long id);
    void save(Coupon coupon);

    void update(Coupon coupon);

    void delete(Long id);

    void changeCouponType(List<Long> ids);
}
