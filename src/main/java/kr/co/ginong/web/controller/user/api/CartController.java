package kr.co.ginong.web.controller.user.api;

import jakarta.servlet.http.HttpSession;
import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.CartService;
import kr.co.ginong.web.service.user.LocationService;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("apiCartController")
@RequestMapping("user/api/cart")
public class CartController {

    @Autowired
    CartService service;

    @Autowired
    private ProductService productService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Cart> list(){

        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;

       List<Cart> list = service.getList(memberId);
       return list;
    }

    @GetMapping("/{prdId}")
    public Cart list(@PathVariable Long prdId){
        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;

        Cart cart = service.get(memberId, prdId);

        return cart;
    }

    @GetMapping("/c")
    public Integer count(){

        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;
        return service.getCount(memberId);
    }


    @PostMapping
    public Boolean add(@RequestBody Long prdId){

        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;
        int qty = 1;
        Cart cart = Cart.builder().productId(prdId).memberId(memberId).quantity(qty).build();
        return service.save(cart);

    }

    @PostMapping("/u")
    public Boolean update(@RequestBody Cart cart){
        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;

        System.out.println(cart.toString());
        Long prdId = cart.getProductId();
        Integer qty = cart.getQuantity();


        if(qty == null || qty == 0)
            return service.edit(memberId, prdId);

        return service.edit(memberId, prdId, qty);
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<Long> list) {
        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;


        return service.delete(memberId,null,list);
    }


}
