package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;

public interface MemberService {

    Member get(String name);
    Member get(Long id);
    Long addMember(Member member);
    boolean addRoute(Long memberId, String joinRoute);
}
