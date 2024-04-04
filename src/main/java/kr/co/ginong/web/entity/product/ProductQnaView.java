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
public class ProductQnaView {
    private long	id;
    private Date	regDate;
    private String 	content;
    private boolean	state;
    private String answerState;

    private String  productId;
    private String  userName;
    private String  productName;
    private String  qnaCategory;
    private String  answer;
}
