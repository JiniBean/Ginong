package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.product.ProductQnaView;

import java.util.List;

public interface QnaService {
    List<ProductQnaView> getProductQna(Long productId);
}
