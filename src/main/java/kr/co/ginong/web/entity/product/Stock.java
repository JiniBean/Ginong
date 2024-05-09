package kr.co.ginong.web.entity.product;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private Long	id;
    private int 	amount;
    private int	    plma;
    private Date	regDate;
    private Date	madeDate;
    private Date	ioDate;
    private String 	desc;
    private Long	memberId;
    private Long	productId;
    private Long	categoryId;


}
