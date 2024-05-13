package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.product.Stock;
import kr.co.ginong.web.entity.product.StockCategory;
import kr.co.ginong.web.entity.product.StockView;
import kr.co.ginong.web.repository.product.StockCategoryRepository;
import kr.co.ginong.web.repository.product.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImp implements StockService{

    @Autowired
    StockRepository repository;

    @Autowired
    StockCategoryRepository categoryRepository;


    @Override
    public List<StockView> getByPrdId(Long productId) {
        return repository.findByPrdId(productId);
    }
    @Override
    public StockView getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<StockView> getList(String query,Boolean amount, Boolean current) {
        return repository.findAll(query,amount, current);
    }

    @Override
    public List<StockCategory> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean add(Stock stock) {
        return repository.save(stock);
    }

    @Override
    public boolean edit(Stock stock) {
        return repository.update(stock);
    }

    @Override
    public Boolean delete(List<Long> ids) {
        return repository.delete(ids);
    }
}
