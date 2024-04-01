package kr.co.ginong.web.entity.cupon;

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
    private Date issuedDate;
    private Date usedDate;
    private long couponId;
    private long memberId;
}
