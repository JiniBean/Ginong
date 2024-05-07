package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.member.Member;

public interface MemberService {

    Member get(String name);
    Member get(Long id);
    Member getByOrderId(Long id);
    
    Long addMember(Member member);
    boolean addRoute(Long memberId, String joinRoute);

    int search(String email, String userName);

    void changePwd(String pwd, String userName);

    Member searchId(String email, String name);

}
