package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponCategory;

import java.util.List;

public interface AdminCouponService {

    List<Coupon> getList();

    Coupon getById(Long id);

    List<CouponCategory> getCategories();

    // coupon 발급 목록 조회
    // List<CouponHistoryView> getHistoryList();
    // impl : AdminCouponService.getHistoryList() { couponService.getList(memberId: Long) }
}
