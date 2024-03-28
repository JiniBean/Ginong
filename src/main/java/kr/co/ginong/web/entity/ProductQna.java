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
public class ProductQna {
    private long	id;
    private Date	regDate;
    private String 	content;
    private boolean	state;
    private boolean answerState;
    private long	memberId;
    private long	productId;
        
}
