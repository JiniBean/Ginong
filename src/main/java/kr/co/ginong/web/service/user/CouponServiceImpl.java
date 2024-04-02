package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import kr.co.ginong.web.repository.coupon.CouponHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponHistoryRepository repository;

    public List<CouponHistoryView> getList(Long memberId) {
        List<CouponHistoryView> list = repository.findAll(memberId);
        return list;
    }

    // 사용 가능 쿠폰 목록 출력
    @Override
    public List<CouponHistoryView> getAvailList(Long memberId) {
        List<CouponHistoryView> list = repository.findAllAvail(memberId);
        return list;
    }

    // 사용 불가능 쿠폰 목록 출력
    @Override
    public List<CouponHistoryView> getUnavailList(Long memberId) {
        List<CouponHistoryView> list = repository.findAllUnavail(memberId);
        return list;
    }

}
