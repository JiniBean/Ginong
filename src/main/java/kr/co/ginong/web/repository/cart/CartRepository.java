package kr.co.ginong.web.repository.cart;

import kr.co.ginong.web.entity.cart.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartRepository {

    List<Cart> findAll(Long memberId);

    List<Map<String, Object>> findAvailableQty(Long memberId);

    Cart find(Long memberId, Long prdId);

    Boolean save(Cart cart);

    Boolean update(Long memberId, Long prdId, Integer qty);

    Integer count(Long memberId);

    Boolean delete(Long memberId, Long prdId, List<Long> list);
}
