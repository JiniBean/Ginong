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
public class Order {
    private long	id;
    private Date	date;
    private int	    type;
    private int		price;
    private int		quantity;
    private String	detailId;
    private long	memberId;
    private long	productId;
    private long	locationId;

}
