package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("user/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PointService pointService;

    @Autowired
    private CouponService couponService;


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
    @PostMapping("info")
    public String info(
            Integer quantity
            ,Long memberId
            ,Long locationId
            ,Long productViewId
            ,Integer price
            ) {
        //난수 제작 :날짜+4자리난수+4자리난수
        //오늘 날짜 제작
        LocalDate currentDate = LocalDate.now();

        // 날짜를 문자열로 변환하기 (yyyyMMdd 형식)
        String dateString = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //난수 두세트 제작
        Random random = new Random();
        int randomNum1 = random.nextInt(10000);
        int randomNum2 = random.nextInt(10000);
        String randomNumber1 = String.format("%04d", randomNum1);
        String randomNumber2 = String.format("%04d", randomNum2);

        String detailId = dateString+"-"+randomNumber1+"-"+randomNumber2;


        //상품정보id, location id order 테이블에 넣기
        Order order = Order.builder()
                .type(1)
                .price(price)
                .quantity(quantity)
                .detailId(detailId)
                .memberId(memberId)
                .productId(productViewId)
                .locationId(locationId)
                .build();

        long id = service.addOrder(order); //auto increment id 값

        return "redirect:pay?id="+id;
    }

        @GetMapping("pay")
    public String pay(
              @RequestParam(name = "id", required = false) Long orderId
            , Model model
    ) {

        // 상품 목록 출력 관련 코드 - 상품 목록 및 총 상품 금액 계산
        List<Order> list = service.get(orderId);
        Order order = list.get(0);
        Long memberId = order.getMemberId();


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

        // 사용가능한 쿠폰 조회
        List<CouponHistoryView> couponList = couponService.getAvailList(memberId);



        // 잔여 적립금 조회
        int point = pointService.getAvailPont(memberId);
        System.out.println(point);

        // 모델
        model.addAttribute("list", list);
        model.addAttribute("prdList", prdList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("point", point);
        model.addAttribute("couponList", couponList);

        System.out.println("counponList = " + couponList.toString());
        System.out.println("counponSize = " + couponList.size());

        return "user/order/pay";
    }

    


}
