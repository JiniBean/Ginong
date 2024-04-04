package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;

public interface MemberService {

    Member getMemberInfo(String name);

    Location getLocation(long id);

    void addLocationHistory(LocationHistory locationHistory);
}
