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
    private Long	id;
    private Integer plma;
    private Integer amount;
    private Date	date;
    private Long	pointId;
    private Long	memberId;
    private Long    orderId;
}
