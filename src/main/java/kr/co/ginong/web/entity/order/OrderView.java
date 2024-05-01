package kr.co.ginong.web.entity.order;

import java.util.Date;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {
    private long	orderId;
    private Date    orderDate;
    private long    orderStatusId;
    private String  orderStatus;
    
    private int     totalAmt;
    private long    paymentId;
    private int     paymentType;
    private String  paymentName;
    private int     refundType;

    private int     deliveryFee;

    private int     couponAmt;
    private int     pointAmt;
}
