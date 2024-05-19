package kr.co.ginong.web.service.cart;

import kr.co.ginong.web.entity.cart.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {

    List<Cart> getList(Long memberId);

    List<Map<String, Object>> getAvailableQtyList(Long memberId);

    Cart get(Long memberId, Long prdId);

    Integer getCount(Long memberId);

    Boolean save(Long memberId, Cart cart, List<Long> list);

    Boolean edit(Long memberId, Long prdId);

    Boolean edit(Long memberId, Long prdId, Integer qty);

    Boolean delete(Long memberId, Long prdId);

    Boolean delete(Long memberId, List<Long> list);
}
