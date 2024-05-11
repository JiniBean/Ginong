package kr.co.ginong.web.controller.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public String signin() {
        return "user/signin";
    }

    @GetMapping("signup/step1")
    public String step1() {
        return "user/signup/step1";
    }

    @GetMapping("signup/step2")
    public String step2(
            @CookieValue(required = false) String userInfo
    ) {

        if (userInfo == null)
            return "redirect:step1";

        //step1의 agree값 있어야만 step2 페이지 열림
        {
            String userInfoStr = URLDecoder.decode(userInfo, Charset.forName("utf-8"));
            boolean hasAgree = userInfoStr.contains("agree");

            if (!hasAgree)
                return "redirect:step1";
        }

        return "user/signup/step2";
    }


    @GetMapping("signup/step3")
    public String step3(
            @CookieValue(required = false) String userInfo
    ) {

        //userInfo가 아예 없다면 step1로
        if (userInfo == null)
            return "redirect:step1";

        //step2의 name값 있어야만 step3 페이지 열림
        {
            String userInfoStr = URLDecoder.decode(userInfo, Charset.forName("utf-8"));
            boolean hasName = userInfoStr.contains("name");

            if (!hasName)
                return "redirect:step2";
        }

        return "user/signup/step3";
    }

    @GetMapping("signup/step4")
    public String step4(
            @RequestParam("name") String name
            , Model model
    ) {
        model.addAttribute("name", name);
        return "user/signup/step4";
    }


    @GetMapping("signup/index")
    public String index() {
        return "user/signup/index";
    }

    @GetMapping("signup/find-id")
    public String signup() {
        return "user/signup/find-id";
    }

    @GetMapping("signup/find-pwd")
    public String findPwd() {
        return "user/signup/find-pwd";
    }

    @GetMapping("signup/confirm-id")
    public String confirmId(HttpServletRequest req, Model model) {
        Cookie[] cookies = req.getCookies(); // 쿠키 배열을 가져옵니다.
        String userName = null;
        String name = null;
        String joinDate = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userName".equals(cookie.getName())) {
                    // 사용자 이름 쿠키를 찾았을 때
                    userName = cookie.getValue();
                    userName = URLDecoder.decode(userName, Charset.forName("utf-8"));
                } else if ("joinDate".equals(cookie.getName())) {
                    // 가입 날짜 쿠키를 찾았을 때
                    joinDate = cookie.getValue();
                    // 날짜 형식을 yyyy-MM-dd로 수정
                    joinDate = joinDate.substring(0, 10);

                } else if ("name".equals(cookie.getName())) {
                    // 이름 쿠키를 찾았을 때
                    name = cookie.getValue();
                    name = URLDecoder.decode(name, Charset.forName("utf-8"));

                }
            }
        }

        model.addAttribute("userName", userName);
        model.addAttribute("joinDate", joinDate);
        model.addAttribute("name", name);

        return "user/signup/confirm-id";
    }


    @GetMapping("signup/change-pwd")
    public String changePwd() {
        return "user/signup/change-pwd";
    }

    @PostMapping("signup/change-pwd")
    public String changePwd(@CookieValue(value = "userName", defaultValue = "") String userName
            , @CookieValue(value = "email", defaultValue = "") String email
            , @RequestParam(value = "password") String pwd
            , @RequestParam(value = "verify-password") String verifyPwd
    ) {



        if (pwd.equals(verifyPwd)) {
            PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
            String encodePwd = pwdEncoder.encode(pwd);
            service.changePwd(encodePwd, userName);
        }
        return "redirect:/signin";
    }
}
