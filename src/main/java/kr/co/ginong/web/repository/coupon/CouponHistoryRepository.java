package kr.co.ginong.web.repository.coupon;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponHistory;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CouponHistoryRepository {

    List<CouponHistoryView> findAll(Long memberId);

    // 사용 가능 쿠폰 목록 출력
    List<CouponHistoryView> findAllAvail(Long memberId);

    // 사용 불가능 쿠폰 목록 출력
    List<CouponHistoryView> findAllUnavail(Long memberId);

    CouponHistoryView findById(Long id);

    void save(CouponHistory history);


    boolean update(CouponHistory history);

    void delete(long id);

    int count(Date usedDate);

}
