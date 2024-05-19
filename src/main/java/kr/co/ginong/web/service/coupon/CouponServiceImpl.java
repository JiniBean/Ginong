package kr.co.ginong.web.service.coupon;

import kr.co.ginong.web.entity.coupon.CouponHistory;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import kr.co.ginong.web.repository.coupon.CouponHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponHistoryRepository historyRepository;


    public List<CouponHistoryView> getList(Long memberId) {
        List<CouponHistoryView> list = historyRepository.findAll(memberId);
        return list;
    }

    // 사용 가능 쿠폰 목록 출력
    @Override
    public List<CouponHistoryView> getAvailList(Long memberId) {
        List<CouponHistoryView> list = historyRepository.findAllAvail(memberId);
        return list;
    }

    // 사용 불가능 쿠폰 목록 출력
    @Override
    public List<CouponHistoryView> getUnavailList(Long memberId) {
        List<CouponHistoryView> list = historyRepository.findAllUnavail(memberId);
        return list;
    }

    @Override
    public boolean updateHistory(CouponHistory history) {

        return historyRepository.update(history);
    }

    @Override
    public Integer getCountCoupon(Long memberId) {
        Integer countCoupon = historyRepository.countByMemberId(memberId);

        return countCoupon;
    }

}
