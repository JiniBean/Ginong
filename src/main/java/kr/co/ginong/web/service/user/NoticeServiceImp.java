package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.repository.notice.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImp {
    private NoticeRepository repository;

    List<Notice> getList() {
        List<Notice> list = repository.findAll();
        return list;
    }

    Notice getById(Long id) {
        Notice notice = repository.findById(id);
        return notice;
    }

}
