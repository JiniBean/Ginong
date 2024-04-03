package kr.co.ginong.web.entity.mypage;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewView {
    private long	id;
    private long    productId;
    private Date	date;
    private boolean	tagClap;
    private boolean	tagFresh;
    private boolean	tagThumbs;
    private String 	content;

    private Long    memberId;
    private String  userName;
    private String  productName;
}
