package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.Location;

public interface MemberService {

    Member getMemberInfo(String name);

    Location getLocation(long id);
}
