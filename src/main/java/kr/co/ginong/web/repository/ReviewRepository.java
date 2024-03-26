package kr.co.ginong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.Review;

@Mapper
public interface ReviewRepository {
    
    List<Review> findAll(Long categoryId
                        , int offset
                        , int size);

    Review findById(Long id);
    
    void save(Review review);
    void update(Review review);
    void delete(long id);

    int count(Long categoryId, String query);
}
