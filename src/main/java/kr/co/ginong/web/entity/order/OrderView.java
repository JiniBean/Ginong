package kr.co.ginong.web.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * Created for OrderList
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {
    private Long	orderId;
    private Long	memberId;
    private Date	orderDate;
    private int	    quantity;
    private int 	price;
    private Long    productId;
    private String  productName;
    private String  imgName;
    private String  imgPath;

}