package kr.co.ginong.web.service.statistics;

import kr.co.ginong.web.entity.member.JoinRoute;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.repository.inquiry.InquiryRepository;
import kr.co.ginong.web.repository.order.OrderRepository;
import kr.co.ginong.web.repository.member.MemberRepository;
import kr.co.ginong.web.repository.product.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImp implements StatisticsService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public Map<String, Object> getOrderStatus() {
        return orderRepository.countStatus();
    }

    @Override
    public List<Map<String, Object>> getStockStatus() {
        return stockRepository.countStatus();
    }

    @Override
    public List<Map<String, Object>> getInquiryStatus() {
        return inquiryRepository.countStatus();
    }

    public List<OrderView> getBestSeller() {
        return orderRepository.findBySalesVolume();
    }

    @Override
    public List<JoinRoute> getJoinRoute() {
        return memberRepository.getJoinRoute();
    }


}

