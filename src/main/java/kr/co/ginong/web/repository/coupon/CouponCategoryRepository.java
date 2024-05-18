package kr.co.ginong.web.repository.coupon;

import kr.co.ginong.web.entity.coupon.CouponCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponCategoryRepository {
    List<CouponCategory> findAll();
}
