package kr.co.ginong.web.entity.coupon;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponHistory {
    private long id;
    private int usedAmt;
    private Date issuedDate;
    private Date usedDate;
    private long couponId;
    private long memberId;
}
