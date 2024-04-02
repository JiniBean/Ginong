package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.coupon.CouponHistoryView;

import java.util.List;

public interface CouponService {

    List<CouponHistoryView> getList(Long memberId);

}
