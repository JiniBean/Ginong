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
public class Exchange {
    private long	id;
    private long	orderId;
    private String 	desc;
    private Date	regDate;
    private int 	quantity;
    private Date	approvalDate;
    private int 	type;
    private long	categoryId;

}
