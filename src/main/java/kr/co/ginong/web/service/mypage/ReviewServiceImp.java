package kr.co.ginong.web.service.mypage;


import kr.co.ginong.web.entity.mypage.ReviewView;
import kr.co.ginong.web.repository.mypage.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService{

    @Autowired
    ReviewRepository repository;

//    @Override
//    public List<ReviewView> getMemberReviews(Long memberId) {
//
//        List<ReviewView> list = reviewRepository.findAll(null,memberId);
//
//        return list;
//    }

    @Override
    public List<ReviewView> getProductReviews(Long productId) {

        List<ReviewView> list = repository.findByProductId(productId);

        return list;
    }

    @Override
    public Integer getCountReview(Long memberId) {
        Integer countReview = repository.countByMemberId(memberId);

        return countReview;
    }

//    @Override
//    public List<ReviewView> getList(Long productId, Long memberId) {
//
//        List<ReviewView> list = reviewRepository.findAll(productId, memberId);
//
//        return list;
//    }

}
