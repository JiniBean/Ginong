package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.notice.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getList();

    Notice getById(Long id);
}
