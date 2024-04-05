package kr.co.ginong.web.repository.mypage;

import java.util.List;

import kr.co.ginong.web.entity.mypage.ReviewView;
import org.apache.ibatis.annotations.Mapper;
import kr.co.ginong.web.entity.mypage.Review;

@Mapper
public interface ReviewRepository {

    List<ReviewView> findAll(Long productId);
    List<ReviewView> findByProductId(Long productId);

    Review findById(Long id);
    
    void save(Review review);
    void update(Review review);
    void delete(long id);

    int count(Long categoryId, String query);
}
