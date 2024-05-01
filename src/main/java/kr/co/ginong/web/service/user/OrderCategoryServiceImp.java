package kr.co.ginong.web.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ginong.web.entity.order.OrderCategory;
import kr.co.ginong.web.repository.order.OrderCategoryRepository;

@Service
public class OrderCategoryServiceImp implements OrderCategoryService{
    @Autowired
    private OrderCategoryRepository repository;
    
    @Override
    public List<OrderCategory> getList() {
        return repository.findAll();
    }
}
