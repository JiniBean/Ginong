package kr.co.ginong.web.repository.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;


@Mapper
public interface LocationRepository {
    List<Location> findAll();

    Location findById(Long id);

    Location findByMemberId(long memberId);
    LocationHistory findByOrderId(long orderId);
    
    void save(Location location);
    void saveHistory(LocationHistory locationHistory);
    void update(Location location);
    void delete(long id);

    int count(Long categoryId, String query);
}
