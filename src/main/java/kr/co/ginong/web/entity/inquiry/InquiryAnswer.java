package kr.co.ginong.web.entity.inquiry;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryAnswer {
    private long	id;
    private String	content;
    private long	adminId;
    private Date	regDate;
    private Date	modDate;
    
}
