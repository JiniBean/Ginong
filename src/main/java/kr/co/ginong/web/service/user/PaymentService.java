package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.order.Payment;

public interface PaymentService {
    Payment getByOrderId(Long orderID);
    boolean add(Payment payment);
}
