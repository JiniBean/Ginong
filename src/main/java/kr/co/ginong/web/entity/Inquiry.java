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
public class Inquiry {
    private long	id;
    private String	title;
    private String	content;
    private long	categoryId;
    private boolean	state;
    private long	memberId;
    private long	orderId;
    private Date	regDate;
    private Date	modDate;
}
