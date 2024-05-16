package kr.co.ginong.web.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FAQController {

    @GetMapping("faq")
    String faq (){
        return "user/faq";
    }
}
