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
public class Refund {
    private Long	id;
    private String 	desc;
    private int	    state;
    private Date	reqDate;
    private Date	completeDate;
    private Long	orderId;
    private Long    orderItemId;
    private Long	categoryId;

}
