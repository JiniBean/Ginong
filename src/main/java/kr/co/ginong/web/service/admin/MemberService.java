package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.member.MemberView;

import java.util.List;

public interface MemberService {

    List<Member> getList(int page, String query); //성능 저하를 피하기 위한 int
    List<Member> getList(int page);

    int getCount(String query);
    int getCount();

    MemberView get(Long memberId);


}
