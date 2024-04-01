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
    private long	id;
    private Date	date;
    private String 	type;
    private int	    totalAmt;
    private long	memberId;
    private long	productId;
    private long	categoryId;
    private long    deliveryFeeCategoryId;
    private long	orderId;
    
}
