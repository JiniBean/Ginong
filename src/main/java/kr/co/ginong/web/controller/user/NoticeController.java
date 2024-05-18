package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.entity.notice.Notice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice")
public class NoticeController {

    @GetMapping("list")
    public String list(Model model) {
        // 카테고리-공지사항 클릭 시, 사용자에게 시각효과를 보여주기 위한 상태값 전달
        model.addAttribute("active", "notice");
        return "user/notice/list";
    }

    @GetMapping("detail")
    public String detail() {
        return "user/notice/detail";
    }

}
