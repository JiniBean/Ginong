package kr.co.ginong.web.service.user;


import kr.co.ginong.web.entity.product.ProductQnaView;
import kr.co.ginong.web.repository.product.ProductQnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QnaServiceImp implements QnaService {

    @Autowired
    ProductQnaRepository qnaRepository;

    @Override
    public List<ProductQnaView> getProductQna(Long productId) {

        List<ProductQnaView> list = qnaRepository.findByProductId(productId);

        return list;
    }
}
