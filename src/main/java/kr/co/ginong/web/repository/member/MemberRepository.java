package kr.co.ginong.web.repository.member;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    Member findByUserName(String name);

    Location findById(long id);

    void saveByLocation(LocationHistory locationHistory);
}
