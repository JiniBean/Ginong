package kr.co.ginong.web.entity.notice;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private long	id;
    private String	title;
    private String	content;
    private Date	startDate;
    private Date	endDate;
    private Date	regDate;
    private long	categoryId;

}
