package kr.co.ginong.web.service.admin;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;
import kr.co.ginong.web.entity.Stock;
import kr.co.ginong.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public void save(Product product
                    , Date madeDate,
                     String amount) {
        System.out.println("==============================");

        repository.save(product);
        /*stock 데이터 넣는 곳*/
        Stock stock = Stock.builder()
                .amount(Integer.parseInt(amount))
                .positive(true)
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

