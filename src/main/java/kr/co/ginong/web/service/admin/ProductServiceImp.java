package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.entity.product.Stock;
import kr.co.ginong.web.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("adminProductServiceImp")
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    int size = 20;

    @Override
    public List<ProductView> getList(int page) {

        int offset = (page-1) * size;

        List<ProductView> list = repository.findAll(null,null, offset, size, null);

        return list;
    }

    @Override
    public void save(Product product
            , Date madeDate,
                     String amount) {
        System.out.println("==============================");

        repository.save(product);
        /*stock 데이터 넣는 곳*/
        Stock stock = Stock.builder()
                .amount(Integer.parseInt(amount))
                .plma(1)
                .madeDate(madeDate)
                .desc(product.getDesc())
                .adminId(1)
                .productId(product.getId())
                .build();
        repository.saveStock(stock);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

    @Override
    public ProductView get(Long productId) {
        ProductView productView = repository.findById(productId);

        return productView;
    }

}

