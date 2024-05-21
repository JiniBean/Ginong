package kr.co.ginong.web.service.inquiry;

import kr.co.ginong.web.entity.inquiry.Inquiry;
import kr.co.ginong.web.entity.inquiry.InquiryCategory;

import java.util.List;

public interface InquiryService {
    Integer getCountInquiry(Long memberId);

    List<Inquiry> getList(Long memberId);

    List<InquiryCategory> getCategoryList();

    void add(Inquiry inquiry);

    void update(Inquiry inquiry);

    Inquiry getById(Long inquiryId);

    void delete(Long inquiryId);
}
