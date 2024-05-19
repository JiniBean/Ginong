package kr.co.ginong.web.service.order;

import kr.co.ginong.web.entity.order.Payment;
import kr.co.ginong.web.repository.order.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService{

    @Autowired
    private PaymentRepository repository;

    @Override
    public Payment getByOrderId(Long orderID) {
        return repository.findByOrderId(orderID);
    }

    @Override
    public boolean add(Payment payment) {
        return repository.save(payment);
    }
}
