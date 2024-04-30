package kr.co.ginong.web.controller.user;

import org.springframework.ui.Model;
import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("user")
@Controller
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping("signin")
    public String signin() {return "user/signin";}

    @GetMapping("signup/step1")
    public String step1() {
        return "user/signup/step1";
    }

    @GetMapping("signup/step2")
    public String step2() {return "user/signup/step2";}

    @GetMapping("signup/step3")
    public String step3() {return "user/signup/step3";}

    @GetMapping("signup/step4")
    public String step4(
            @RequestParam("name")String name
            , Model model
    ) {
        model.addAttribute("name",name);
        return "user/signup/step4";
    }


    @GetMapping("signup/index")
    public String index() {return "user/signup/index";}

    @GetMapping("signup/find-id")
    public String signup() {
        return "user/signup/find-id";}

    @GetMapping("signup/find-pwd")
    public String findPwd() {
        return "user/signup/find-pwd";}

    @GetMapping("signup/confirm-id")
    public String confirmId() {return "user/signup/confirm-id";}

    @GetMapping("signup/change-pwd")
    public String changePwd() {return "user/signup/change-pwd";}

    @GetMapping("signup5/confirm-pwd")
    public String confirmPwd5() {return "user/signup/confirm-pwd";}

}
