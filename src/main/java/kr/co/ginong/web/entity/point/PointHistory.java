package kr.co.ginong.web.entity.point;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointHistory {
    private long	id;
    private int     plma;
    private int 	amount;
    private Date	date;
    private long	pointId;
    private long	memberId;
    private long    orderId;
}
