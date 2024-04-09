package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@Controller
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping("signin")
    public String signin() {return "user/signin";}

    @GetMapping("signup/step1")
    public String signup() {return "user/signup/step1";}

}
