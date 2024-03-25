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
public class ProductQnaAnswer {
    private long	id;
    private Date	content;
    private String 	adminId;
    private boolean	regDate;
    private long	modDate;
    
}
