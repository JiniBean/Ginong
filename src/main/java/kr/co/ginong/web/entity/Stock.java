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
public class Stock {
    private long	id;
    private int 	amount;
    private boolean	positive;
    private boolean	negative;
    private Date	regDate;
    private Date	madeDate;
    private Date	ioDate;
    private String 	desc;
    private long	adminId;
    private long	productId;

}
