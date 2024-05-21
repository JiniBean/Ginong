package kr.co.ginong.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminPointController")
@RequestMapping("admin/point")
public class PointController {

    @GetMapping("history/list")
    public String list(Model model) {
        return "admin/point/HistoryList";
    }


    @GetMapping("history/detail")
    public String detail(Model model) {
        String pageName = "포인트 상세";
        model.addAttribute("pageName", pageName);
        return "admin/point/HistoryDetail";
    }

    @GetMapping("/payment")
    public String payment(){
        return "admin/point/payment";
    }


}
