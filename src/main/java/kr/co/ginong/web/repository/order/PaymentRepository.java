package kr.co.ginong.web.repository.order;

import kr.co.ginong.web.entity.order.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PaymentRepository {
    List<Payment> findAll();

    Payment findByOrderId(Long orderId);
    
    boolean save(Payment payment);
    boolean update(Payment payment);
    boolean delete(long id);

}
