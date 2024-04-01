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
public class Faq {
    private long	id;
    private String	title;
    private String	content;
    private boolean	state;
    private Date	regDate;
    private long	adminId;
    private long	faqCategoryId;
    private long	orderId;
    
}
