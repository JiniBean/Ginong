package kr.co.ginong.web.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {

    @GetMapping("faq")
    String faq (Model model){
        // 카테고리-고객센터 클릭 시, 사용자에게 시각효과를 보여주기 위한 상태값 전달
        model.addAttribute("active","faq");
        return "user/faq";
    }
}
