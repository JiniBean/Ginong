package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.member.MemberView;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.entity.point.Point;
import kr.co.ginong.web.repository.member.AdminMemberRepository;
import kr.co.ginong.web.repository.order.LocationRepository;
import kr.co.ginong.web.repository.order.OrderRepository;
import kr.co.ginong.web.repository.point.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminMemberServiceImp")
public class MemberServiceImp implements MemberService {

    @Autowired
    private AdminMemberRepository repository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OrderRepository orderRepository;


    int size = 20;

    @Override
    public List<Member> getList(int page, String query) {
        int offset = (page - 1) * size;
        List<Member> list = repository.findAll(query, offset, size);

        return list;
    }

    @Override
    public List<Member> getList(int page) {
        return getList(page, null);
    }

    @Override
    public int getCount(String query) {
        int count = repository.count(query);

        return count;
    }

    @Override
    public int getCount() {
        int count = repository.count(null);

        return count;
    }

    @Override
    public MemberView get(Long memberId) {

        MemberView member = repository.findById(memberId);

        return member;
    }


    @Override
    public int getTotalMemberCount() {

        int totalMember = repository.getTotalMemberCount();

        return totalMember;
    }

    @Override
    public void update(MemberView member) {

        Member updatedMemberInfo = new Member();
        updatedMemberInfo.setId(member.getId());
        updatedMemberInfo.setUserName(member.getUserName());
        updatedMemberInfo.setEmail(member.getEmail());
        updatedMemberInfo.setPhone(member.getPhone());

        Point updatedPointInfo = new Point();
        updatedPointInfo.setMemberId(member.getId());
        updatedPointInfo.setCost(member.getCost());

        Location updatedLocationInfo = new Location();
        updatedLocationInfo.setMemberId(member.getId());
        updatedLocationInfo.setAddr1(member.getAddr1());
        updatedLocationInfo.setAddr2(member.getAddr2());


        repository.updateMemberByMemberId(updatedMemberInfo);
        pointRepository.updatePointByMemberId(updatedPointInfo);
        locationRepository.updateLocationByMemberId(updatedLocationInfo);

    }

    @Override
    public List<OrderView> getOrderList(Long memberId) {

        List<OrderView> orderList = orderRepository.findByMemberId(memberId);

        return orderList;
    }


}
