package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.order.Payment;
import kr.co.ginong.web.repository.order.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService{

    @Autowired
    private PaymentRepository repository;
    @Override
    public void add(Payment payment) {
        repository.save(payment);
    }
}
