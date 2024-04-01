package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.MemberService;
import kr.co.ginong.web.service.user.OrderService;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @GetMapping("info")
    public String info(Model model
                        ,@RequestParam(name="productId") Long productId
                        ,@RequestParam(name="quantity") Long quantity
    ){

        //(바로 구매하기) 상품정보리스트 가져오기
        System.out.println("=====================");
        System.out.println("productId="+productId);
        System.out.println("quantity="+quantity);

        //상품정보 가져오기
        ProductView productView =  productService.get(productId);

        System.out.println(productView.toString());

        //총상품값 계산해서 넣기+++

        model.addAttribute("productView", productView);
        model.addAttribute("totalQuantity", quantity);

        //================================================================
        String name ="dmswls";

        Member member =  memberService.getMemberInfo(name);
        model.addAttribute("memberList", member);

        //배송지정보 가져오기
        Location location = memberService.getLocation(member.getId());
        model.addAttribute("location",location);

        return "user/order/info";
    }

    @GetMapping("pay")
    public String pay(
            @RequestParam(name = "id", required = false) Long id
            , Model model
    ) {
        Long id_ = 1L;
        id = id_;
        List<Order> list = service.get(1L);
        List<ProductView> prdList = new ArrayList<>();

        int totalPrice = 0;
        for(Order o : list){
            long prdId = o.getProductId();
            ProductView prd = productService.get(prdId);
            prdList.add(prd);

            int price = o.getPrice();
            int quantity = o.getQuantity();
            int total = price * quantity;
            totalPrice += total;
        }


        System.out.println("list.toString() = " + list.toString());

        model.addAttribute("list", list);
        model.addAttribute("prdList", prdList);
        model.addAttribute("totalPrice", totalPrice);

        return "user/order/pay";
    }


}
