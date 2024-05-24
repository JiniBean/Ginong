package kr.co.ginong.web.entity.product;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private Long	id;
    private int	    plma;
    private int 	amount;
    private Date	regDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date	madeDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date	ioDate;

    private String 	desc;
    private Long	memberId;
    private Long	productId;
    private Long	categoryId;
    private Long	orderId;


}
