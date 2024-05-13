package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.repository.notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiNoticeController")
@RequestMapping("api/notices")
public class NoticeController {

    @Autowired
    private NoticeRepository repository;

    @GetMapping
    public List<Notice> list() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Notice detail(@PathVariable(required = false) Long id) {
        return repository.findById(id);
    }
}
