package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.CouponHistory;
import kr.co.ginong.web.entity.Location;

@Mapper
public interface CouponHistoryRepository {
    List<CouponHistory> findAll();

    CouponHistory findById(Long id);
    
    void save(Location location);
    void update(Location location);
    void delete(long id);

    int count(Long categoryId, String query);
}
