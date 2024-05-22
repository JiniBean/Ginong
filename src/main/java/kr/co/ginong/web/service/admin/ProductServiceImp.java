package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminProductServiceImp")
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    int size = 20;

    // @Override
    // public List<ProductView> getList(int page) {
    //     return getList(page, null);
    // }

    // public List<ProductView> getList(int page, String query) {
    //     int offset = (page-1) * size;
    //     List<ProductView> list = repository.findAll(null, query, offset, size, null);

    //     return list;
    // }

    public List<ProductView> getListforAdmin(int page) {
        return getListforAdmin(page, null);
    }

    public List<ProductView> getListforAdmin(int page, String query) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAllforAdmin(null, query, offset, size, null);

        return list;
    }

    @Override
    public ProductView get(Long productId) {
        ProductView productView = repository.findById(productId);

        return productView;
    }    

    @Override
    public int getCount() {
        int count = repository.count(null, null);
        return count;
    }

    public int getCount(String query) {
        int count = repository.count(null, query);
        return count;
    }

    
    public void delete(Long productId) {
        repository.delete(productId);
    }

    
    public void save(Product product){
        repository.save(product);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

    @Override
    public void hidden(List<Long> ids) {
        repository.updateState(ids);
    }
}

