package kr.co.ginong.web.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockView {
    private Long	stockId;
    private Long	productId;
    private String  name;
    private String  prettyName;
    private int 	currentAmount;
    private int     quantity;
    private String  quantityCategoryName;
    private int     weight;
    private String  weightCategoryName;
    private int	    plma;
    private String  symbol;
    private int 	amount;
    private Date	ioDate;
    private Date	madeDate;
    private Date	regDate;
    private String 	desc;
    private Long	stockCategoryId;
    private String  stockCategoryName;

}
