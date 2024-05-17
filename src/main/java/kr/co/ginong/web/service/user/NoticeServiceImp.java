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

    @Override
    public List<Notice> getList() {
        List<Notice> list = repository.findAll();
        return list;
    }

    @Override
    public Notice getById(Long id) {
        Notice notice = repository.findById(id);
        return notice;
    }

    @Override
    public List<NoticeCategory> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void insert(Notice notice) {
        repository.save(notice);
    }
   
    @Override
    public void update(Long noticeId, Notice notice) {
        repository.update(noticeId, notice);
    }

    @Override
    public void delete(Long noticeId, Notice notice) {
        repository.delete(noticeId, notice);
    }
}
