package kr.co.ginong.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inquiry")
public class InquiryController {

    @GetMapping("list")
    String list(Model model) {

        String pageName="1:1 문의내역";
        //모델 및 세션
        model.addAttribute("pageName", pageName);

        return "/user/inquiry/list";
    }

    @GetMapping("detail")
    String detail(Model model){
        String pageName="1:1 문의하기";
        //모델 및 세션
        model.addAttribute("pageName", pageName);
        return "/user/inquiry/detail";
    }

    @GetMapping("reg")
    String reg(Model model){
        String pageName="1:1 문의하기";
        //모델 및 세션
        model.addAttribute("pageName", pageName);
        return "/user/inquiry/reg";
    }

    @GetMapping("update")
    String update(){ return "/user/inquiry/update"; }

}
