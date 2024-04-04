package kr.co.ginong.web.repository.order;

import java.util.List;

import kr.co.ginong.web.entity.order.LocationHistory;
import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.order.Location;


@Mapper
public interface LocationRepository {
    List<Location> findAll();

    Location findById(Long id);
    
    void save(Location location);
    void saveByLocation(LocationHistory locationHistory);
    void update(Location location);
    void delete(long id);

    int count(Long categoryId, String query);
}
