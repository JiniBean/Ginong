package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.entity.member.MemberView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("adminPointController")
@RequestMapping("admin/point/history")
public class PointController {

    @GetMapping("list")
    public String list(Model model) {
        return "admin/point/history/list";
    }


    @GetMapping("/detail")
    public String detail(){
        return "admin/point/history/detail";
    }

//    @PostMapping("/detail")
//    public String detail(MemberView member) {
//
//        return "redirect:/admin/point/history/detail?="+member.getId();
//    }

    @GetMapping("/payment")
    public String payment(){
        return "admin/point/history/payment";
    }


}
