package kr.co.ginong.web.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * Created for OrderItemView List
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemView {
    private Long	orderId;
    private Date	orderDate;
    private Long    productId;
    private String  productName;
    private int 	price;
    private int	    quantity;
    private String  imgName;
    private String  imgPath;
    private int     weight;
    private String  weightCategory;
}