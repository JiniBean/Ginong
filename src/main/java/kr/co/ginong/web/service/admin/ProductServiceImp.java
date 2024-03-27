package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;
import kr.co.ginong.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminProductServiceImp")
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public List<ProductView> getList() {

        return null;
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

}

