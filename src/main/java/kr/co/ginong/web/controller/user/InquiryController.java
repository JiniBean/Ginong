package kr.co.ginong.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inquiry")
public class InquiryController {

    @GetMapping("list")
    String list() {
        return "/user/inquiry/list";
    }


}
