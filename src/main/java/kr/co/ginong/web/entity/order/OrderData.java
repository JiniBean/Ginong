package kr.co.ginong.web.entity.order;

import kr.co.ginong.web.entity.coupon.CouponHistory;
import kr.co.ginong.web.entity.order.Payment;
import kr.co.ginong.web.entity.point.PointHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    private Payment payment;

    private CouponHistory couponHistory;

    private PointHistory pointHistory;

    private Order order;

    private List<OrderItem> orderItem;

    private LocationHistory locationHistory;
}
