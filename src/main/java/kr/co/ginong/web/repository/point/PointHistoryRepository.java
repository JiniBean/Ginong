package kr.co.ginong.web.repository.point;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.point.PointHistory;
import kr.co.ginong.web.entity.point.PointHistoryView;

@Mapper
public interface PointHistoryRepository {

    List<PointHistoryView> findAll(Long memberId);
    int calc(Long memberId);

//    PointHistoryView findById(Long id);
    
    boolean save(PointHistory pointHistory);
    void update(PointHistory pointHistory);
    void delete(long id);

}