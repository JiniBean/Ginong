package kr.co.ginong.web.controller.user;

import jakarta.servlet.http.HttpSession;
import kr.co.ginong.web.entity.coupon.Coupon;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.*;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private LocationService locationService;


    @GetMapping("info")
    public String info(Model model
            , @RequestParam(name = "p") Long prdId
            , @RequestParam(name = "q") Integer qty
            , HttpSession session
    ) {

        //상품정보 가져오기
        List<OrderItem> items = new ArrayList<>();
        OrderItem item = OrderItem.builder().productId(prdId).quantity(qty).build();
        items.add(item);


        //총상품값 계산해서 넣기
        List<ProductView> prdList = new ArrayList<>();

        int totalPrice = 0;
        for (OrderItem i : items) {
            long pId = i.getProductId();
            ProductView prd = productService.get(pId);
            prdList.add(prd);

            int price = prd.getPrice();
            i.setPrice(price);

            int quantity = i.getQuantity();
            int total = price * quantity;
            totalPrice += total;
        }

        //================================================================
        String name = "dmswls"; //로그인 구현 전이라 박아놓은 MEMBER_NAME

        Member member = memberService.get(name);
        Long mId = member.getId();


        //배송지정보 가져오기
        Location location = locationService.getByMemberID(mId);

        //모델 및 세션
        model.addAttribute("location", location);
        model.addAttribute("member", member);
        model.addAttribute("list", items);
        model.addAttribute("prdList", prdList);
        model.addAttribute("totalPrice", totalPrice);

        session.setAttribute("orderItems", items);
        session.setAttribute("memberId", mId);


        return "user/order/info";
    }

    @PostMapping("info")
    public String info(
            LocationHistory locationHistory
            , HttpSession session
    ) {

        List<OrderItem> items = (List<OrderItem>) session.getAttribute("orderItems");
        Long memberId = (Long) session.getAttribute("memberId");

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

        String str = dateString + randomNumber1 + randomNumber2;
        Long id = Long.parseLong(str);

//        상품정보id, location id order 테이블에 넣기
        Order order = Order.builder()
                .id(id)
                .type(1)
                .memberId(memberId)
                .locationId(locationHistory.getLocationId())
                .build();


        boolean vaild = service.add(order);
        if (vaild){
            for (OrderItem i : items)
                i.setOrderId(id);

            service.addItems(items);
        }

        //LOCATION_HISTORY 테이블에 넣기
        locationHistory.setCategoryId(1);
        locationHistory.setMemberId(memberId);
        locationHistory.setOrderId(id);

        locationService.addHistory(locationHistory);

        //난수로 만든 detail_id로 pay로 주소보내기
        return "redirect:pay?o=" + id;
    }

    @GetMapping("pay")
    public String pay(
            @RequestParam(name = "o", required = false) Long orderId
            , Model model
    ) {

        // 상품 목록 출력 관련 코드 - 상품 목록 및 총 상품 금액 계산
        List<OrderItem> items = service.getItems(orderId);
        List<Order> list = service.get(orderId);

        Long memberId = list.get(0).getMemberId();

        List<ProductView> prdList = new ArrayList<>();

        int totalPrice = 0;
        for (OrderItem i : items) {
            long prdId = i.getProductId();
            ProductView prd = productService.get(prdId);
            prdList.add(prd);

            int price = i.getPrice();
            int quantity = i.getQuantity();
            int total = price * quantity;
            totalPrice += total;
        }

        // 사용가능한 쿠폰 조회
        List<CouponHistoryView> couponList = couponService.getAvailList(memberId);

        // 잔여 적립금 조회
        int point = pointService.getAvailPont(memberId);

        // 모델
        model.addAttribute("list", list);
        model.addAttribute("prdList", prdList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("point", point);
        model.addAttribute("couponList", couponList);

        return "user/order/pay";
    }

    @PostMapping("pay")
    public String pay(
            Payment payment,
            Long couponId,
            Integer point
    ){

        //memberID setting (추후 세션으로 바뀔지도)
        Long orderId = payment.getOrderId();
        Order order = service.get(orderId).get(0);
        Long memberId = order.getMemberId();
        payment.setMemberId(memberId);

        //couponHistory update

        //pointHistory update


        return "redirect:complete";
    }


    @GetMapping("complete")
    public String complete() {

        return "user/order/complete";
    }



}
