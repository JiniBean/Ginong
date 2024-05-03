package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.Charset;

@RequestMapping()
@Controller
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping("/signin")
    public String signin() {return "user/signin";}

    @GetMapping("signup/step1")
    public String step1() {
        return "user/signup/step1";
    }

    @GetMapping("signup/step2")
    public String step2(
            @CookieValue(required = false) String userInfo
    ) {

        if(userInfo==null)
            return "redirect:signup/step1";

        //step1의 agree값 있어야만 step2 페이지 열림
       {
            String userInfoStr = URLDecoder.decode(userInfo, Charset.forName("utf-8"));
            boolean hasAgree = userInfoStr.contains("agree");

            if(!hasAgree)
                return "redirect:signup/step1";
        }

        return "user/signup/step2";
    }


    @GetMapping("signup/step3")
    public String step3(
            @CookieValue(required = false) String userInfo
    ) {

        //userInfo가 아예 없다면 step1로
        if(userInfo==null)
            return "redirect:signup/step1";

        //step2의 name값 있어야만 step3 페이지 열림
        {
            String userInfoStr = URLDecoder.decode(userInfo, Charset.forName("utf-8"));
            boolean hasName = userInfoStr.contains("name");

            if(!hasName)
                return "redirect:signup/step2";
        }

        return "user/signup/step3";
    }

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
    public String changePwd() {return "user/signup/change-pwd";
    }
    @PostMapping("signup/change-pwd")
    public String changePwd(@CookieValue(value = "userName", defaultValue = "") String userName
                            ,@CookieValue(value = "email", defaultValue = "") String email
                            ,@RequestParam(value = "password") String pwd
                            ,@RequestParam(value = "verify-password") String verifyPwd
                            ){

        if(pwd.equals(verifyPwd)) {
            service.changePwd(pwd,userName);
        }
        return "redirect:/signin";
    }

    @GetMapping("signup5/confirm-pwd")
    public String confirmPwd5() {return "user/signup/confirm-pwd";}

}
