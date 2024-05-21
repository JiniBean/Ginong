package kr.co.ginong.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("coupon")
public class CouponController {

    @GetMapping("list")
    public String list(Model model) {

        String pageName="쿠폰 사용내역";

        model.addAttribute("pageName", pageName);
        return "user/coupon/list";
    }
}
