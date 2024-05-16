package kr.co.ginong.web.controller.admin;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("adminCouponController")
@RequestMapping("admin/coupon")
public class CouponController {

    // @Autowired
    // private NoticeService service;

    @GetMapping("list")
    public String list() {
        return "admin/coupon/list";
    }

    @GetMapping("detail")
    public String detail() {
        return "admin/coupon/detail";
    }

    @GetMapping("reg")
    public String reg() {
        return "admin/coupon/reg";
    }
}
