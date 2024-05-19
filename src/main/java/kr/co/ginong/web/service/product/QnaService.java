package kr.co.ginong.web.service.product;

import kr.co.ginong.web.entity.product.ProductQnaView;

import java.util.List;

public interface QnaService {
    List<ProductQnaView> getProductQna(Long productId);
}
