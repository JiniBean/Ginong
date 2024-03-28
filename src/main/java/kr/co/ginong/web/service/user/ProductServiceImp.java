package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;
import kr.co.ginong.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    int size = 12;
    @Override
    public List<ProductView> getList(Integer page) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(null, null, offset, size);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, String query) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(null, query, offset, size);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, Long categoryId) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, null, offset, size);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, Long categoryId, String query) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, query, offset, size);

        return list;
    }

    @Override
    public int count() {
        int count = repository.count(null,null);
        return count;
    }

    @Override
    public int count(Long categoryId) {
        int count = repository.count(categoryId,null);
        return count;
    }

    @Override
    public int count(String query) {
        int count = repository.count(null,query);

        return count;
    }

    @Override
    public int count(Long categoryId, String query) {
        int count = repository.count(categoryId,query);

        return count;
    }

    @Override
    public ProductView get(Long productId) {
        ProductView productView = repository.findById(productId);

        return productView;
    }
}
