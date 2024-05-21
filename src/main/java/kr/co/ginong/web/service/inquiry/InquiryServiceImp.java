package kr.co.ginong.web.service.inquiry;

import kr.co.ginong.web.entity.inquiry.Inquiry;
import kr.co.ginong.web.entity.inquiry.InquiryCategory;
import kr.co.ginong.web.repository.inquiry.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImp implements InquiryService {

    @Autowired
    InquiryRepository repository;

    @Override
    public Integer getCountInquiry(Long memberId) {
        Integer countInquiry = repository.countByMemberId(memberId);

        return countInquiry;
    }

    @Override
    public List<Inquiry> getList(Long memberId) {
        List<Inquiry> list = repository.findByMemberId(memberId);
        return list;
    }

    @Override
    public List<InquiryCategory> getCategoryList() {
        List<InquiryCategory> categoryList = repository.findAllCategory();

        return categoryList;
    }

    @Override
    public void add(Inquiry inquiry) {
        repository.save(inquiry);
    }

    @Override
    public void update(Inquiry inquiry) {repository.update(inquiry);
    }

    @Override
    public Inquiry getById(Long inquiryId) {
        Inquiry list = repository.findById(inquiryId);
        return list;
    }

    @Override
    public void delete(Long inquiryId) {repository.deleteById(inquiryId);}

}
