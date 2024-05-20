package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.service.cart.CartService;
import kr.co.ginong.web.service.order.LocationService;
import kr.co.ginong.web.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<Cart> list(@AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(userDetails!=null)
            memberId = userDetails.getId();

       List<Cart> list = service.getList(memberId);
       return list;
    }

    @GetMapping("/{prdId}")
    public Cart list(@PathVariable Long prdId,
                     @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(userDetails!=null)
            memberId = userDetails.getId();

        Cart cart = service.get(memberId, prdId);

        return cart;
    }


//    @GetMapping("/available-qty/{memberId}")
    @GetMapping("/available-qty")
    public List<Map<String, Object>> availableQtyList(@AuthenticationPrincipal WebUserDetails userDetails){
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

        // WebUserDetails 에서 memberId 가져오기
        Long memberId = null;
        if(userDetails != null)
            memberId = userDetails.getId();
        System.out.println("################################################");
        System.out.println(service.getAvailableQtyList(memberId));
        System.out.println("################################################");
        return service.getAvailableQtyList(memberId);
    }

    @GetMapping("/c")
    public Integer count(@AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(userDetails!=null)
            memberId = userDetails.getId();
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
    public Boolean add(@RequestBody Cart cart,
                       @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;

        if(userDetails!=null)
            memberId = userDetails.getId();

        return service.save(memberId, cart, null);

    }

    @PostMapping("list")
    public Boolean addList(@RequestBody List<Long> list,
                       @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;

        if(userDetails!=null)
            memberId = userDetails.getId();

        return service.save(memberId, null,list);

    }

    @PostMapping("/u")
    public Boolean update(@RequestBody Cart cart,
                          @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(userDetails!=null)
            memberId = userDetails.getId();

        Long prdId = cart.getProductId();
        Integer qty = cart.getQuantity();

        if(qty == null || qty == 0)
            return service.edit(memberId, prdId);

        return service.edit(memberId, prdId, qty);
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<Long> list,
                          @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(userDetails!=null)
            memberId = userDetails.getId();

        return service.delete(memberId,list);
    }


}
