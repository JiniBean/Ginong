package kr.co.ginong.web.repository.inquiry;

import kr.co.ginong.web.entity.inquiry.Inquiry;
import kr.co.ginong.web.entity.inquiry.InquiryCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryRepository {

    Integer countByMemberId(Long memberId);

    List<Inquiry> findByMemberId(Long memberId);

    List<InquiryCategory> findAllCategory();

    void save(Inquiry inquiry);

    void update(Inquiry inquiry);

    Inquiry findById(Long inquiryId);


    void deleteById(Long inquiryId);
}
