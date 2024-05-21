package kr.co.ginong.web.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("adminCouponController")
@RequestMapping("admin/coupon")
public class CouponController {

    // @Autowired
    // private NoticeService service;

    @GetMapping("list")
    public String list(Model model) {
        String pageName = "쿠폰 관리";
        model.addAttribute("pageName", pageName);
        return "admin/coupon/list";
    }

    @GetMapping("detail")
    public String detail(Model model) {
        String pageName = "쿠폰 상세";
        model.addAttribute("pageName", pageName);
        return "admin/coupon/detail";
    }

    @GetMapping("reg")
    public String reg(Model model) {
        String pageName = "쿠폰 등록";
        model.addAttribute("pageName", pageName);
        return "admin/coupon/reg";
    }

    @GetMapping("update")
    public String update(Model model) {
        String pageName = "쿠폰 수정";
        model.addAttribute("pageName", pageName);
        return "admin/coupon/update";
    }
}
