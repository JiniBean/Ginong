package kr.co.ginong.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/mypage")
public class PointController {

    @GetMapping("/point")
    public String point(Model model) {

        return "user/mypage/point";
    }


}
