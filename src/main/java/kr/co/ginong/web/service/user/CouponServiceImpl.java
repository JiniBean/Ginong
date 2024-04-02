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

}
