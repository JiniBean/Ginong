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
public class ReviewView {
    private long	id;
    private Date	date;
    private boolean	tagBest;
    private boolean	tagFresh;
    private boolean	tagThumbs;
    private String 	content;

    private String  userName;
    private String  productName;
}
