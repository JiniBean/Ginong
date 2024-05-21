package kr.co.ginong.web.entity.order;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long	id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date	date;
    private String  trackingNum;
    private Long	memberId;
    private Long	categoryId;
    private Long	locationId;
    private Long	cancelId;

}
