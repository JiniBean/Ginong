package kr.co.ginong.web.repository.member;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.member.MemberView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMemberRepository {

    List<Member> findAll(String query, int offset, int size);

    int count( String query);

    MemberView findById(Long memberId);
//    MemberView findById(Long id);

    int getTotalMemberCount();

    void updateMember(Member updatedMemberInfo);
}
