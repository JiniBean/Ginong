package kr.co.ginong.web.controller.user;

import jakarta.servlet.http.HttpSession;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.service.cart.CartService;
import kr.co.ginong.web.service.order.LocationService;
import kr.co.ginong.web.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            Model model){

        String pageName="장바구니";
        model.addAttribute("pageName", pageName);
        return "user/cart";
    }

    @PostMapping
    public String list(@RequestParam("location") Long locationId,
                       @RequestParam(name = "chkId", required = false) List<Long> chkIds,
                       @RequestParam("prdId") List<Long> prdIds,
                       @RequestParam("quantity") List<Integer> qtys,
                       @RequestParam("all") Boolean isAll,
                       HttpSession session){

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
