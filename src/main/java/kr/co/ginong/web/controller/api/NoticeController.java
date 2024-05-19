package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.entity.notice.NoticeCategory;
import kr.co.ginong.web.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiNoticeController")
@RequestMapping("api/notices")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @GetMapping
    public List<Notice> list() {
        return service.getList();
    }

    @GetMapping("/{noticeId}")
    public Notice detail(@PathVariable(required = false) Long noticeId) {
        return service.getById(noticeId);
    }

    @GetMapping("notice-category")
    public List<NoticeCategory> categoryList() {
        return service.getCategories();
    }

    @PutMapping("/{noticeId}")
    public void updateNotice(@PathVariable Long noticeId, @RequestBody Notice notice) {
        service.update(notice);
    }

    @PostMapping()
    public void addNotice(@RequestBody Notice notice) {
        service.insert(notice);
    }

    @DeleteMapping("/{noticeId}")
    public void deleteNotice(@PathVariable(name = "noticeId") Long id) {
        service.delete(id);
    }

}
