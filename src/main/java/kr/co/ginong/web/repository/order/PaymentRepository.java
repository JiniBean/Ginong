package kr.co.ginong.web.repository.order;

import kr.co.ginong.web.entity.order.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PaymentRepository {
    List<Payment> findAll();

    Payment findById(Long id);
    
    void save(Payment payment);
    void update(Payment payment);
    void delete(long id);

}
