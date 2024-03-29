package kr.co.ginong.web.entity;

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
    private boolean	state;
    private int		price;
    private int		quantity;
    private long	detailId;
    private long	memberId;
    private long	productId;
    private long	locationId;

}
