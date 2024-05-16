package kr.co.ginong.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class RecentController {
    @GetMapping("recent/list")
    public String list() {
        return "user/recent/list";
    }



}
