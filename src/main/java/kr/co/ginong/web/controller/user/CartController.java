package kr.co.ginong.web.controller.user;

import jakarta.servlet.http.HttpSession;
import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.CartService;
import kr.co.ginong.web.service.user.LocationService;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
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
            HttpSession session,
            Model model){

        // 임시 멤버 정보 로그인 완성 후 수정 예정
        Long memberId = 2L;

        List<Cart> items = new ArrayList<>();
        Location location = new Location();

        // 상품 리스트 갖고 오기, 회원 정보가 없다면 세션에서
        if(memberId != null){
            items = service.getList(memberId);
            location = locationService.getByMemberID(memberId);
        }
        else {
            items = (List<Cart>) session.getAttribute("cartList");
            location.setId(0);
            location.setAddr1("");
            location.setAddr2("");
        }

        // 화면에 뿌려줄 장바구니 정보와 상품 정보 조합하기
        List<Map<String, Object>> list = new ArrayList<>();
        int totalPrice = 0;

        for (Cart i : items) {
            long pId = i.getProductId();
            ProductView p = productService.get(pId);

            //총상품값 계산해서 넣기
            int price = p.getPrice();
            int quantity = i.getQuantity();
            int total = price * quantity;
            totalPrice += total;

            //장바구니 정보 + 상품 정보 List<Map>으로 만들기
            Map<String, Object> map = new HashMap<>();
            map.put("id", pId);
            map.put("name", p.getName() + ", " + p.getQuantity() + p.getQuantityCategory() + "(" + p.getWeight() + p.getWeightCategory() + ")");
            map.put("img", p.getThumbnailPath() + p.getThumbnailName());
            map.put("price", price);
            map.put("quantity", quantity);
            list.add(map);
        }

        model.addAttribute("list", list);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("location", location);
        model.addAttribute("count", items.size());
        return "user/cart";
    }

    @PostMapping
    public String list(@RequestParam("location") Long locationId,
                       @RequestParam(value = "chkId", required = false) List<Long> chkIds,
                       @RequestParam("prdId") List<Long> prdIds,
                       @RequestParam("quantity") List<Integer> qtys,
                       @RequestParam("all") Boolean isAll,
                       HttpSession session){

        // 임시로 박아놓음, 로그인 완성 후 수정 예정
//        Long memberId = null;
        Long memberId = 2L;

        // 회원 정보가 없다면 로그인 페이지로
        if(memberId==null)
            return "redirect:/user/signin";

        List<OrderItem> list = new ArrayList<>();

        // 전체 상품 주문하기 일 경우
        if(isAll){
            for (int i=0; i<qtys.size(); i++){
               OrderItem item = new OrderItem();
               item.setProductId( prdIds.get(i) );
               item.setQuantity( qtys.get(i) );
               list.add(item);
            }
        }
        // 선택 상품 주문하기 일 경우
        else{
            for (Long p : chkIds){
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
}
