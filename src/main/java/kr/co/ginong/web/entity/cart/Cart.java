package kr.co.ginong.web.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long  id;
    private int   quantity;
    private Long  productId;
    private Long  memberId;

}
