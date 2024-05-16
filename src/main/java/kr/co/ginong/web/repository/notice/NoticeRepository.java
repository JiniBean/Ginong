package kr.co.ginong.web.repository.notice;

import kr.co.ginong.web.entity.notice.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeRepository {

    List<Notice> findAll();

    Notice findById(Long id);

    void save(Notice notice);
    
    void update(Long noticeId, Notice notice);
}
