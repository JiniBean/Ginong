package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.point.PointHistory;

@Mapper
public interface PointHistoryRepository {
    List<PointHistory> findAll();

    /* id로 후기 찾기 */
    PointHistory findById(Long id);
    
    void save(PointHistory pointHistory);
    void update(PointHistory pointHistory);
    void delete(long id);

    int count(Long categoryId, String query);
    
}