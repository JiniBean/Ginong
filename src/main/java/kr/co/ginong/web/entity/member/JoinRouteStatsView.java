package kr.co.ginong.web.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRouteStatsView {
    // 유입량 집계를 위해 필요한 View. highchart를 사용하려면 'count' 필드가 필요함
    private String  name;
    private int     count;
}
