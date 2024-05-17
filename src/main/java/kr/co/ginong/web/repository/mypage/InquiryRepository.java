package kr.co.ginong.web.repository.mypage;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryRepository {

    Integer countByMemberId(Long memberId);
}
