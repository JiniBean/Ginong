package kr.co.ginong.web.repository.member;

import kr.co.ginong.web.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    Member find(Long id,String name);
    Member findByOrderId(Long orderId);

    Long insertMember(Member member);

    int insertRoute(Long memberId, String name);


    int searchByNameAndMail(String email, String userName);

    Member searchByRealNameAndMail(String email, String name);

    void updateByUserName(String pwd, String userName);

    Member findByUsername(String username);

}
