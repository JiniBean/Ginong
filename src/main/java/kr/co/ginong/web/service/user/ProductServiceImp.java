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
    public ProductView get(Long productId) {
        ProductView productView = repository.findById(productId);

        return productView;
    }

    public List<ProductView> getList(Integer page,  Long categoryId, String query, Integer sortType) {
        int offset = (page-1) * size;
        List<ProductView> list = repository.findAll(categoryId, query, offset, size, sortType);

        return list;
    }

    @Override
    public List<ProductView> getSeasonOutList() {
        // 미사용. 향후 확장성을 위해 준비함
        List<ProductView> seasonOutList = repository.findAllSeasonOut();
        return seasonOutList;
    }

    @Override
    public List<ProductView> getSeasonOutList(Integer page, Long categoryId, String query, Integer sortType) {
        // 미사용. 향후 확장성을 위해 준비함
        int offset = (page-1) * size;
        List<ProductView> seasonOutList = repository.findAllSeasonOut(categoryId, query, offset, size, sortType);
        return seasonOutList;
    }

    @Override
    public List<ProductView> getCommingSoonList() {
        // 미사용. 향후 확장성을 위해 준비함
        List<ProductView> commingSoonList = repository.findAllCommingSoon();
        return commingSoonList;
    }

    @Override
    public List<ProductView> getCommingSoonList(Integer page, Long categoryId, String query, Integer sortType) {
        // 미사용. 향후 확장성을 위해 준비함
        int offset = (page-1) * size;
        List<ProductView> commingSoonList = repository.findAllCommingSoon(categoryId, query, offset, size, sortType);
        return commingSoonList;
    }

    public int count(Long categoryId, String query) {
        int count = repository.count(categoryId, query);

        return count;
    }





}
