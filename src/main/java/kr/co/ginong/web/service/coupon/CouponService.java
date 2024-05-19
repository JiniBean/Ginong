package kr.co.ginong.web.service.coupon;

import kr.co.ginong.web.entity.coupon.CouponHistory;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;

import java.util.List;

public interface CouponService {

    List<CouponHistoryView> getList(Long memberId);

    // 사용 가능한 쿠폰 출력
    List<CouponHistoryView> getAvailList(Long memberId);
    
    // 사용 불가능한(이미 사용한) 쿠폰 출력
    List<CouponHistoryView> getUnavailList(Long memberId);

    boolean updateHistory(CouponHistory history);

    Integer getCountCoupon(Long memberId);
}
