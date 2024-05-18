package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponCategory;
import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.entity.notice.NoticeCategory;
import kr.co.ginong.web.service.admin.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiCouponController")
@RequestMapping("api/coupons")
public class CouponController {
    @Autowired
    private CouponService service;

    @GetMapping
    public List<Coupon> list() {
        return service.getList();
    }
    @GetMapping("coupon-category")
    public List<CouponCategory> categoryList() {
        return service.getCategories();
    }

    @GetMapping("/{couponId}")
    public Coupon detail(@PathVariable(required = false) Long couponId) {
        return service.getById(couponId);
    }
}
