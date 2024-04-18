package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.cart.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getList(Long memberId);

    Cart get(Long memberId, Long prdId);

    Integer getCount(Long memberId);

    Boolean save(Cart cart);

    Boolean edit(Long memberId, Long prdId);
    Boolean edit(Long memberId, Long prdId, Integer qty);
}
