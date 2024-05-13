package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.entity.notice.NoticeCategory;
import kr.co.ginong.web.repository.notice.NoticeCategoryRepository;
import kr.co.ginong.web.repository.notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImp implements NoticeService {
    @Autowired
    private NoticeRepository repository;

    @Autowired
    private NoticeCategoryRepository categoryRepository;

    public List<Notice> getList() {
        List<Notice> list = repository.findAll();
        return list;
    }

    public Notice getById(Long id) {
        Notice notice = repository.findById(id);
        return notice;
    }

    public List<NoticeCategory> getCategories() {
        return categoryRepository.findAll();
    }

}
