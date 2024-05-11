package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.service.user.CartService;
import kr.co.ginong.web.service.user.LocationService;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("apiCartController")
@RequestMapping("api/cart")
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


    @GetMapping("/available-qty/{memberId}")
    public List<Map<String, Object>> availableQtyList(@PathVariable Long memberId){

        // memberId가 null이면 memberId = 2L => 테스트용
        if(memberId == null) memberId = 2L;

        // service.getAvailableQtyList(memberId) 설명

        // DB 결과집합 예시
        // PRODUCT_ID|       NAME|  PRICE|CART_QUANTITY|STOCK_QUANTITY|STOCK_STATUS|             IMG|
        // ----------+-------+-----+-------------+--------------+------------+----------------+
        //         94| 텃밭 흙 당근|   5000|            8|           277|       valid|/img/carrot.png |
        //         95| 텃밭 흙 감자|   4500|            4|            70|       valid|/img/photato.jpg|
        //         96|  돈사밭 배추|   8500|            2|           172|       valid|/img/cabbage.jpg|

        // 위 결과 집합을 다음과 같이 반환 ( List<Map<String, Object>> )

        // [
        // { "PRODUCT_PRICE": 5000, "IMG": "/img/carrot.png", "STOCK_STATUS": "valid", "CART_QUANTITY": 8, "PRODUCT_ID": 94, "PRODUCT_NAME": "텃밭 흙 당근", "STOCK_QUANTITY": 277},
        // { "PRODUCT_PRICE": 4500, "IMG": "/img/photato.jpg", "STOCK_STATUS": "valid", "CART_QUANTITY": 4, "PRODUCT_ID": 95, "PRODUCT_NAME": "텃밭 흙 감자", "STOCK_QUANTITY": 70},
        // { "PRODUCT_PRICE": 8500, "IMG": "/img/cabbage.jpg", "STOCK_STATUS": "valid", "CART_QUANTITY": 2, "PRODUCT_ID": 96, "PRODUCT_NAME": "돈사밭 배추", "STOCK_QUANTITY": 172}
        // ]

        return service.getAvailableQtyList(memberId);
    }

    @GetMapping("/c")
    public Integer count(){

        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;
        return service.getCount(memberId);
    }

    @GetMapping("/location/{memberId}")
    public Location location(@PathVariable Long memberId){
        // memberId가 null이면 memberId = 2L => 테스트용
        if(memberId == null) memberId = 2L;

        Location location = locationService.getByMemberID(memberId);

        return location;
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

        Long prdId = cart.getProductId();
        Integer qty = cart.getQuantity();

        System.out.println("prdId:"+prdId);
        System.out.println("qty:"+qty);

        if(qty == null || qty == 0)
            return service.edit(memberId, prdId);

        return service.edit(memberId, prdId, qty);
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<Long> list) {
        // 임시로 박아놓음, 로그인 완성 후 수정 예정
        Long memberId = 2L;


        return service.delete(memberId,list);
    }


}
