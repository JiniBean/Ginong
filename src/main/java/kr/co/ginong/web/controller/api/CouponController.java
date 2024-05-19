package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponCategory;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import kr.co.ginong.web.service.admin.AdminCouponService;
import kr.co.ginong.web.service.user.CouponService;
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
    private AdminCouponService adminService;

    @Autowired
    private CouponService couponService;

    @GetMapping
    public List<Coupon> list() {
        return adminService.getList();
    }

    @GetMapping("coupon-category")
    public List<CouponCategory> categoryList() {
        return adminService.getCategories();
    }

    @GetMapping("/{couponId}")
    public Coupon detail(@PathVariable(name = "couponId") Long id) {
        return adminService.getById(id);
    }

    /* ============================== user coupon ================================= */
    @GetMapping("/available/{memberId}")
    public List<CouponHistoryView> getAvailList(@PathVariable(name = "memberId") Long id) {
        return couponService.getAvailList(id);
    }

    @GetMapping("/unavailable/{memberId}")
    public List<CouponHistoryView> getUnavailList(@PathVariable(name = "memberId") Long id) {
        return couponService.getUnavailList(id);
    }
}
