package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.mypage.ReviewView;

import java.util.List;

public interface ReviewService {

    List<ReviewView> getProductReviews(Long productId);

    Integer getCountReview(Long memberId);
//    List<ReviewView> getMemberReviews(Long memberId);
//    List<ReviewView> getList(Long productId, Long memberId);

}
