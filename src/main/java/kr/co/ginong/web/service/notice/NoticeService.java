package kr.co.ginong.web.service.notice;

import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.entity.notice.NoticeCategory;

import java.util.List;

public interface NoticeService {
    List<Notice> getList();

    Notice getById(Long id);

    List<NoticeCategory> getCategories();

    void insert(Notice notice);
    void update(Notice notice);
    void delete(Long id);

    
}
