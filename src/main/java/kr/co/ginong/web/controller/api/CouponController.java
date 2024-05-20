package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponCategory;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.service.admin.AdminCouponService;
import kr.co.ginong.web.service.coupon.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{couponId}")
    public void updateCoupon(@PathVariable(name = "couponId") Long id, @RequestBody Coupon coupon) {
        adminService.update(coupon);
    }

    @PostMapping()
    public void addCoupon(@RequestBody Coupon coupon) {
        adminService.insert(coupon);
    }

    @DeleteMapping("/{couponId}")
    public void deleteNotice(@PathVariable(name = "couponId") Long id) {
        adminService.delete(id);
    }

    // 비활성화 된 쿠폰 활성화 하기
    @PutMapping("enable")
    public void enableCoupon(@RequestBody List<Long> ids) {
        adminService.changeCouponType(ids);
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
