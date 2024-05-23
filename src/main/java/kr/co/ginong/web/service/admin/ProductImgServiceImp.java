package kr.co.ginong.web.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ginong.web.entity.product.ProductImg;
import kr.co.ginong.web.repository.product.ProductImgRepository;

@Service
public class ProductImgServiceImp implements ProductImgService {

    @Autowired
    private ProductImgRepository repository;

    @Override
    public void add(ProductImg productImg) {
       repository.save(productImg);
    }
    
}
