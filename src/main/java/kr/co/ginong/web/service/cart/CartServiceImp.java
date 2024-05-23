package kr.co.ginong.web.service.cart;

import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    CartRepository repository;

    @Override
    public List<Cart> getList(Long memberId) { return repository.findAll(memberId); }

    @Override
    public List<Map<String, Object>> getAvailableQtyList(Long memberId) { return repository.findAvailableQty(memberId); }

    @Override
    public Cart get(Long memberId, Long prdId) { return repository.find(memberId,prdId); }

    @Override
    public Integer getCount(Long memberId){return repository.count(memberId);}

    @Override
    public Boolean save(Long memberId, Cart cart, List<Long> list) { return repository.save(memberId, cart, list); }

    @Override
    public Boolean saveWhenLogin(List<Cart> carts) { return repository.saveWhenLogin(carts); }

    @Override
    public Boolean edit(Long memberId, Long prdId) { return repository.update(memberId,prdId, null); }

    @Override
    public Boolean edit(Long memberId, Long prdId, Integer qty) { return repository.update(memberId,prdId, qty); }

    @Override
    @Transactional
    public Boolean editWhenLogin(List<Cart> carts) {
        // 비회원 로그인 시 장바구니에(쿠키/DB) 같은 상품이 존재할 때, 쿠키에 있는 상품 개수로 업데이트 하는 기능
        // mapper에서 foreach로 update 하려고 했으나, sql 구문 오류로 인해서 서비스단에서 foreach 돌리는 방식으로 변경했습니다.
        for (Cart cart : carts) {
            if (!repository.update(cart.getMemberId(), cart.getProductId(), cart.getQuantity())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean delete(Long memberId, Long prdId) { return repository.delete(memberId, prdId, null); }

    @Override
    public Boolean delete( Long memberId, List<Long> list) {  return repository.delete(memberId, null, list); }
}
