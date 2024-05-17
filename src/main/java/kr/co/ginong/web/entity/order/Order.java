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
public class Order {
    private Long	id;
    private Date	date;
    private int	    type;
    private Long	memberId;
    private Long	locationId;
    private Long	cancelId;

}
