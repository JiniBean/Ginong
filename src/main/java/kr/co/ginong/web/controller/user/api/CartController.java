package kr.co.ginong.web.controller.user.api;

import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.service.user.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiCartController")
@RequestMapping("user/api/cart")
public class CartController {

    @Autowired
    CartService service;

    @GetMapping
    public List<Cart> list(){

        // 로그인 완성 후 수정 예정
        Long memberId = 2L;

       List<Cart> list = service.getList(memberId);
       return list;
    }

    @GetMapping("/{prdId}")
    public Cart list(@PathVariable Long prdId){
        // 로그인 완성 후 수정 예정
        Long memberId = 2L;

        Cart cart = service.get(memberId, prdId);

        return cart;
    }

    @GetMapping("/c")
    public Integer count(){

        // 로그인 완성 후 수정 예정
        Long memberId = 2L;
        return service.getCount(memberId);
    }


    @GetMapping("/a/{prdId}")
    public Boolean add(@PathVariable Long prdId
            , @RequestParam(name = "q", required = false) Boolean addCount){

        // 로그인 완성 후 수정 예정
        Long memberId = 2L;

        if(addCount != null) {
            return service.edit(memberId, prdId);
        }
        int qty = 1;
        Cart cart = Cart.builder().productId(prdId).memberId(memberId).quantity(qty).build();
        return service.save(cart);

    }

    @DeleteMapping
    public Boolean delte(List<Long> list){
        return service.delete(null,list);
    }


}
