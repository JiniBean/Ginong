package kr.co.ginong.web.repository.member;

import kr.co.ginong.web.entity.member.MemberRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRoleRepository {
    List<MemberRole> findAllByMemberId(Long memberId);
}
