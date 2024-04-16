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


    @PostMapping("/{prdId}")
    public boolean add(@PathVariable Long prdId
            ,@RequestParam(name = "q", required = false) Boolean addCount){

        System.out.println("왔어");
        // 로그인 완성 후 수정 예정
        Long memberId = 2L;

        if(addCount != null) {
            System.out.println("숫자만 추가해");
            return service.edit(memberId, prdId);
        }
        int qty = 1;
        System.out.println("건너왔어 다 추가해");
        Cart cart = Cart.builder().productId(prdId).memberId(memberId).quantity(qty).build();
        return service.save(cart);

    }


}
