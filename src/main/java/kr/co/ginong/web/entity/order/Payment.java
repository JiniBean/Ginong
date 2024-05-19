package kr.co.ginong.web.entity.order;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long	id;
    private Date	date;
    private String 	type;
    private Integer totalAmt;
    private Boolean refundType;
    private Long	memberId;
    private Long	categoryId;
    private Long    deliveryFeeCategoryId;
    private Long	orderId;

    private Integer couponAmt;
    private Integer pointAmt;
    private Integer deliveryFee;
    private String  categoryName;
}
