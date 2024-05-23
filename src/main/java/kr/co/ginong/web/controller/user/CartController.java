package kr.co.ginong.web.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.service.cart.CartService;
import kr.co.ginong.web.service.order.LocationService;
import kr.co.ginong.web.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService service;

    @Autowired
    private ProductService productService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public String list(
            Model model) {

        String pageName = "장바구니";
        model.addAttribute("pageName", pageName);
        return "user/cart";
    }

    @PostMapping
    public String list(@RequestParam("location") Long locationId,
                       @RequestParam(name = "chkId", required = false) List<Long> chkIds,
                       @RequestParam("prdId") List<Long> prdIds,
                       @RequestParam("quantity") List<Integer> qtys,
                       @RequestParam("all") Boolean isAll,
                       HttpSession session) {

        List<OrderItem> list = new ArrayList<>();

        // 전체 상품 주문하기 일 경우
        if (isAll) {
            for (int i = 0; i < qtys.size(); i++) {
                OrderItem item = new OrderItem();
                item.setProductId(prdIds.get(i));
                item.setQuantity(qtys.get(i));
                list.add(item);
            }
        }
        // 선택 상품 주문하기 일 경우
        else {
            for (Long p : chkIds) {
                OrderItem item = new OrderItem();
                item.setProductId(p);

                int idx = prdIds.indexOf(p);
                item.setQuantity(qtys.get(idx));
                list.add(item);
            }
        }

        session.setAttribute("orderItems", list);
        session.setAttribute("locationId", locationId);

        return "redirect:/order/info";
    }

    @GetMapping("/add")
    public String addCart(HttpServletRequest req
                        , HttpServletResponse res
                        , @AuthenticationPrincipal WebUserDetails userDetails
                         ) throws UnsupportedEncodingException, JsonProcessingException {

        // db 장바구니 조회
        Long memberId = userDetails.getId();
        List<Cart> cartListDB = service.getList(memberId);

        // 쿠키 장바구니 조회
        List<Cart> cartListCookie = getCartCookie(req, memberId);

        boolean isUpdateOrInsert = false;  // 플래그를 사용하여 업데이트 또는 인서트 확인

        // 쿠키에 장바구니 데이터 없으면 skip
        if (!cartListCookie.isEmpty()) {
            // DB의 장바구니 목록과 쿠키의 장바구니 목록을 비교 ( 기존 장바구니에 담겨있는 상품 = UPDATE / 새로 담은 상품 = INSERT )
            List<Cart> cartUpdate = new ArrayList<>();
            List<Cart> cartInsert = new ArrayList<>();

            for (Cart cartCookie : cartListCookie) {
                boolean found = false;
                for (Cart cart : cartListDB) {
                    if (cart.getProductId().equals(cartCookie.getProductId())) {
                        // DB에 이미 존재하는 항목이면 업데이트 목록에 추가
                        cart.setQuantity(cartCookie.getQuantity());
                        cartUpdate.add(cart);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // DB에 존재하지 않는 항목이면 INSERT 목록에 추가
                    cartInsert.add(cartCookie);
                }
            }

            if (!cartInsert.isEmpty()) {
                service.saveWhenLogin(cartInsert);
                isUpdateOrInsert = true;
            }
            if (!cartUpdate.isEmpty()) {
                service.editWhenLogin(cartUpdate);
                isUpdateOrInsert = true;
            }
        }

        // 업데이트 또는 인서트가 수행되었다면 쿠키를 삭제
        if (isUpdateOrInsert) {
            Cookie cookie = new Cookie("cartList", "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            res.addCookie(cookie);
        }

        return "redirect:/index";
    }


    // 쿠키에 담겨있는 카트 정보 가져오는 메소드 JSON -> Cart Entity
    protected List<Cart> getCartCookie(HttpServletRequest request, Long userId) throws UnsupportedEncodingException, JsonProcessingException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            // 쿠키가 존재 하지 않는 경우.
            return new ArrayList<>();
        }

        for (Cookie cookie : cookies) {
            if ("cartList".equals(cookie.getName())) {
                // 쿠키 값을 디코딩
                String decodedValue = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());

                // JSON 문자열을 Map 리스트로 변환
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> cartDataList = objectMapper.readValue(decodedValue, new TypeReference<List<Map<String, Object>>>() {
                });

                // Map 리스트를 Cart 객체 리스트로 변환
                List<Cart> cartList = new ArrayList<>();
                for (Map<String, Object> cartData : cartDataList) {
                    Cart cart = Cart.builder()
                            .productId(((Number) cartData.get("prdId")).longValue())
                            .quantity((Integer) cartData.get("quantity"))
                            .memberId(userId)
                            .build();
                    cartList.add(cart);
                }
                return cartList;
            }
        }

        //cartList 가 없는 경우
        return new ArrayList<>();
    }

}
