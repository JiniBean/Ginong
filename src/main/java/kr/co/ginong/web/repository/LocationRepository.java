package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.Location;


@Mapper
public interface LocationRepository {
    List<Location> findAll();

    Location findById(Long id);
    
    void save(Location location);
    void update(Location location);
    void delete(long id);

    int count(Long categoryId, String query);
}
