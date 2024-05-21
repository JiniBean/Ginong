package kr.co.ginong.web.repository.coupon;

import kr.co.ginong.web.entity.coupon.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponRepository {
    List<Coupon> findAll(String query);
    Coupon findById(Long id);
    void save(Coupon coupon);

    void update(Coupon coupon);

    void delete(Long id);

    void changeCouponType(List<Long> ids);
}
