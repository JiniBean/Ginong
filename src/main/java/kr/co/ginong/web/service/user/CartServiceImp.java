package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    CartRepository repository;

    @Override
    public List<Cart> getList(Long memberId) {
        return repository.findAll(memberId);
    }

    @Override
    public Cart get(Long memberId, Long prdId) {
        return repository.find(memberId,prdId);
    }

    @Override
    public Boolean save(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public Boolean edit(Long memberId, Long prdId) {
        System.out.println("서비스야");

        return repository.update(memberId,prdId, null);
    }

    @Override
    public Boolean edit(Long memberId, Long prdId, Integer qty) {
        return repository.update(memberId,prdId, qty);
    }
}
