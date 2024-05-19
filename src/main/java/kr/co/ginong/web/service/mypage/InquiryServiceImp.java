package kr.co.ginong.web.service.mypage;

import kr.co.ginong.web.repository.mypage.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImp implements InquiryService{

    @Autowired
    InquiryRepository repository;

    @Override
    public Integer getCountInquiry(Long memberId) {
        Integer countInquiry = repository.countByMemberId(memberId);

        return countInquiry;
    }
}
