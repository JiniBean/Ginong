package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponCategory;

import java.util.List;

public interface CouponService {

    List<Coupon> getList();

    Coupon getById(Long id);

    List<CouponCategory> getCategories();
}
