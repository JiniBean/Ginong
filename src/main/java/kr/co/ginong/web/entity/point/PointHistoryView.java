package kr.co.ginong.web.entity.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointHistoryView {

    private long	id;
    private String 	plma;
    private int 	amount;
    private Date    pointHistoryDate;
    private long	memberId;
    private long    orderId;
    private long	pointId;
    private String  pointName;
    private Date    pointRegDate;
    private int	    cost;
    private String 	detail;
    private boolean	state;
    private String  memberName;
    private String  userName;
    private String    birthDate;
    private String  phone;

}
