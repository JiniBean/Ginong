package kr.co.ginong.web.repository.mypage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.mypage.Review;

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
