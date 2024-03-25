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
public class Wishlist {
    private Date	date;
    private Boolean	state;
    private long	memberId;
    private long	productId;
    
}
