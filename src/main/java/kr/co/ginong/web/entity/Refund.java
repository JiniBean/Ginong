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
public class Refund {
    private long	id;
    private String 	reason;
    private boolean	state;
    private Date	reqDate;
    private Date	completeDate;
    private long	orderId;
    private long	categoryId;

}
