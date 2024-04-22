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

    public List<ProductView> getList(Integer page,  Long categoryId, String query, Integer sortType) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, query, offset, size, sortType);

        return list;
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
