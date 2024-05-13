package kr.co.ginong.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminNoticeController")
@RequestMapping("admin/notice")
public class NoticeController {

    @GetMapping("list")
    public String list() {
        return "admin/notice/list";
    }
}
