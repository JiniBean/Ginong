package kr.co.ginong.web.service.cart;

import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Cart get(Long memberId, Long prdId) {
        return repository.find(memberId,prdId);
    }

    @Override
    public Integer getCount(Long memberId){return repository.count(memberId);}
    @Override
    public Boolean save(Long memberId, Cart cart, List<Long> list) {
        return repository.save(memberId, cart, list);
    }

    @Override
    public Boolean edit(Long memberId, Long prdId) {
        return repository.update(memberId,prdId, null);
    }

    @Override
    public Boolean edit(Long memberId, Long prdId, Integer qty) {
        return repository.update(memberId,prdId, qty);
    }

    @Override
    public Boolean delete(Long memberId, Long prdId) {
        return repository.delete(memberId, prdId, null);
    }

    @Override
    public Boolean delete( Long memberId, List<Long> list) {
        return repository.delete(memberId, null, list);
    }
}
