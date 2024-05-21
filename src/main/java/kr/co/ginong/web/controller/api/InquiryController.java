package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.inquiry.Inquiry;
import kr.co.ginong.web.entity.inquiry.InquiryCategory;
import kr.co.ginong.web.entity.notice.Notice;
import kr.co.ginong.web.service.inquiry.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiInquiryController")
@RequestMapping("api/inquiries")
public class InquiryController {

    @Autowired
    InquiryService service;

    @GetMapping("list")
    public List<Inquiry> list(@AuthenticationPrincipal WebUserDetails userDetails){
        Long memberId = 0L;
        if (userDetails != null)
            memberId = userDetails.getId();
        List<Inquiry> list = service.getList(memberId);


        return list;
    }
    @GetMapping("memberId")
    public Long memberId(@AuthenticationPrincipal WebUserDetails userDetails){
        Long memberId = 0L;
        if (userDetails != null)
            memberId = userDetails.getId();
        return memberId;
    }
    @GetMapping("/{inquiryId}")
    public Inquiry detail(@PathVariable Long inquiryId){return service.getById(inquiryId);}

    @GetMapping("category")
    public List<InquiryCategory> categoryList(){
        List<InquiryCategory> categoryList = service.getCategoryList();

        return categoryList;
    }

    @PostMapping()
    public void addInquiry(@RequestBody Inquiry inquiry){service.add(inquiry);}

    @PutMapping("/{inquiryId}")
    public void updateInquiry(@PathVariable Long inquiryId, @RequestBody Inquiry inquiry) {
        inquiry.setId(inquiryId);
        service.update(inquiry);
    }

    @DeleteMapping("/{inquiryId}")
    public void deleteInquiry(@PathVariable Long inquiryId) {
        service.delete(inquiryId);
    }

}
