package kr.co.ginong.web.service.user;


import kr.co.ginong.web.dto.Pager;
import kr.co.ginong.web.dto.ProductFilter;
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
        // List<ProductView> list = repository.findAll(null, null, offset, size, null);
        // return list;
        return getList(page, null, null, null);
    }

    @Override
    public List<ProductView> getList(Integer page, Integer sortType) {
        int offset = (page-1) * size;
        // List<ProductView> list = repository.findAll(null, null, offset, size, sortType);
        // return list;
        return getList(page, null, null, sortType);
    }

    @Override
    public List<ProductView> getList(Integer page, String query) {
        int offset = (page-1) * size;
        // List<ProductView> list = repository.findAll(null, query, offset, size, null);
        // return list;
        return getList(page, query, null, null);
    }

    @Override
    public List<ProductView> getList(Integer page, Long categoryId) {
        int offset = (page-1) * size;
        // List<ProductView> list = repository.findAll(categoryId, null, offset, size, null);
        // return list;
        return getList(page, null, categoryId, null);
    }
    
    @Override
    public List<ProductView> getList(Integer page, Long categoryId, String query) {
        int offset = (page-1) * size;
        // List<ProductView> list = repository.findAll(categoryId, query, offset, size, null);
        // return list;
        return getList(page, query, categoryId, null);
    }

    @Override
    public List<ProductView> getList(Integer page, String query, Integer sortType) {
        int offset = (page-1) * size;
        // List<ProductView> list = repository.findAll(null, query, offset, size, sortType);
        // return list;
        return getList(page, query, null, sortType);
    }
    
    @Override
    public List<ProductView> getList(Integer page, Long categoryId, Integer sortType) {
        int offset = (page-1) * size;
        // List<ProductView> list = repository.findAll(categoryId, null, offset, size, sortType);
        // return list;

        return getList(page, null, categoryId, sortType);
    }

    public List<ProductView> getList(Integer page, String query, Long categoryId, Integer sortType) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, query, offset, size, sortType);

        return list;
    }

    // pager : size, page를 담은 dto 객체
    // productFilter : categoryId, query, sortType을 담은 dto 객체
    @Override
    public List<ProductView> getList(ProductFilter productFilter, Pager pager) {
        long categoryId = productFilter.getCategoryId();
        String query = productFilter.getQuery();
        int offset = pager.getOffset();
        int size = pager.getSize();
        int sortType = productFilter.getSortType();

        System.out.println(size);
        return repository.findAll(categoryId, query, offset, size, sortType);
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

    @Override
    public int count(ProductFilter productFilter) {
        long categoryId = productFilter.getCategoryId();
        String query = productFilter.getQuery();

        return repository.count(categoryId, query);
    }
    public int count(Long categoryId, String query) {
        int count = repository.count(categoryId, query);

        return count;
    }

    @Override
    public ProductView get(Long productId) {
        ProductView productView = repository.findById(productId);

        return productView;
    }

}
