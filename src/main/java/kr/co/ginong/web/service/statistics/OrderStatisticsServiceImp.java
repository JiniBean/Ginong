package kr.co.ginong.web.service.statistics;

import kr.co.ginong.web.entity.member.JoinRoute;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.repository.order.OrderRepository;
import kr.co.ginong.web.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderStatisticsServiceImp implements OrderStatisticsService{

    @Autowired
    private OrderRepository repository;

    @Autowired MemberRepository memberRepository;

    @Override
    public Map<String, Object> getStatus() {
        return repository.countStatus();
    }

    public List<OrderView> getBestSeller() {
        return repository.findBySalesVolume();
    }

    @Override
    public List<JoinRoute> getJoinRoute() {
        return memberRepository.getJoinRoute();
    }
}
