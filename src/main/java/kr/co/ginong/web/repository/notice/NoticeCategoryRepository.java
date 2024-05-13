package kr.co.ginong.web.repository.notice;

import kr.co.ginong.web.entity.notice.NoticeCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeCategoryRepository {
    public List<NoticeCategory> findAll();
}
