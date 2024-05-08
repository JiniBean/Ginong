package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.product.StockView;
import kr.co.ginong.web.repository.product.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImp implements StockService{

    @Autowired
    StockRepository repository;

    @Override
    public StockView get(Long productId) {
        return repository.findById(productId);
    }

    @Override
    public List<StockView> getList() {
        return repository.findAll();
    }
}
