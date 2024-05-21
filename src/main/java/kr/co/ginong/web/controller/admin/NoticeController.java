package kr.co.ginong.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminNoticeController")
@RequestMapping("admin/notice")
public class NoticeController {

    @GetMapping("list")
    public String list(Model model) {
        String pageName = "공지 관리";
        model.addAttribute("pageName", pageName);
        return "admin/notice/list";
    }

    @GetMapping("detail")
    public String detail(Model model) {
        String pageName = "공지 수정";
        model.addAttribute("pageName", pageName);
        return "admin/notice/detail";
    }

    @GetMapping("reg")
    public String reg(Model model) {
        String pageName = "공지 등록";
        model.addAttribute("pageName", pageName);
        return "admin/notice/reg";
    }
}
