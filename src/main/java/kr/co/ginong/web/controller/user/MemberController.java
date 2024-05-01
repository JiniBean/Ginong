package kr.co.ginong.web.controller.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
    public String step2(
            @CookieValue(required = false) String userInfo
    ) {

        if(userInfo==null)
            return "redirect:/user/signup/step1";

        //step1의 agree값 있어야만 step2 페이지 열림
       {
            String userInfoStr = URLDecoder.decode(userInfo, Charset.forName("utf-8"));
            boolean hasAgree = userInfoStr.contains("agree");

            if(!hasAgree)
                return "redirect:user/signup/step1";
        }

        return "user/signup/step2";
    }


    @GetMapping("signup/step3")
    public String step3(
            @CookieValue(required = false) String userInfo
    ) {

        //userInfo가 아예 없다면 step1로
        if(userInfo==null)
            return "redirect:/user/signup/step1";

        //step2의 name값 있어야만 step3 페이지 열림
        {
            String userInfoStr = URLDecoder.decode(userInfo, Charset.forName("utf-8"));
            boolean hasName = userInfoStr.contains("name");

            if(!hasName)
                return "redirect:/user/signup/step2";
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
        System.out.println("사용자 이름: " + userName);
        System.out.println("이메일: " + email);
        System.out.println("비밀번호: " + pwd);
        System.out.println("비밀번호확인: " + verifyPwd);
        if(pwd.equals(verifyPwd)) {
            service.changePwd(pwd,userName);
        }
        return "redirect:/user/signin";
    }

    @GetMapping("signup5/confirm-pwd")
    public String confirmPwd5() {return "user/signup/confirm-pwd";}

}
