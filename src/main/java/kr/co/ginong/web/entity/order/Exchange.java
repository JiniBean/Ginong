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
public class Exchange {
    private Long	id;
    private String 	desc;
    private int 	type;
    private int 	quantity;
    private Date	regDate;
    private Date	approvalDate;
    private Long	categoryId;

}
