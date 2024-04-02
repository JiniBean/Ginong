package kr.co.ginong.web.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponHistoryView {
    private long    id;
    private Date    issuedDate;
    private Date    usedDate;
    private long    memberId;
    private long    couponId;
    private String  categoryName;
    private String  couponName;
    private long    discountAmount;
    private String    discountUnit;
    private long    maxPaymentAmt;
    private long    minPaymentAmt;
    private Date    startDate;
    private Date    endDate;
    private int     releaseAmount;
    private String  desc;
    private boolean state;
    private Date    regDate;


}
