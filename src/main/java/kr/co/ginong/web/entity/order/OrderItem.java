package kr.co.ginong.web.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long	id;
    private int		price;
    private int		quantity;
    private Long	productId;
    private Long	orderId;
    private Long	exchangeId;
    private Long	refundId;

}
