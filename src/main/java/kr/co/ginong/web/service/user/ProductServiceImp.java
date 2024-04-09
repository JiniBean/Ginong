package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    final int size = 12;
    @Override
    public List<ProductView> getList(Integer page) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(null, null, offset, size, null);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, Integer sortType) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(null, null, offset, size, sortType);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, String query) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(null, query, offset, size, null);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, Long categoryId) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, null, offset, size, null);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, String query, Integer sortType) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(null, query, offset, size, sortType);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, String query, Long categoryId) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, query, offset, size, null);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, Long categoryId, String query) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, query, offset, size, null);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, Long categoryId, Integer sortType) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, null, offset, size, sortType);

        return list;
    }

    @Override
    public List<ProductView> getList(Integer page, String query, Long categoryId, Integer sortType) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, query, offset, size, sortType);

        return list;
    }

    @Override
    public int count() {
        return count(null, null);
    }

    @Override
    public int count(Long categoryId) {
        return count(categoryId, null);
    }

    @Override
    public int count(String query) {
        return count(null, query);
    }

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
