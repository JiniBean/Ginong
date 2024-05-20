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
public class Coupon {
    private long	id;
    private long	couponCategoryId;
    private String	name;
    private long	discountAmount;
    private String	discountUnit;
    private long	minPaymentAmt;
    private long	maxDiscountAmt;
    private Date	startDate;
    private Date	endDate;
    private int	    releaseAmount;
    private String	desc;
    private int 	type;
    private Date	regDate;


}
