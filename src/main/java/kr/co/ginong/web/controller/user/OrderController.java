package kr.co.ginong.web.controller.user;

import jakarta.servlet.http.HttpSession;
import kr.co.ginong.web.entity.coupon.CouponHistory;
import kr.co.ginong.web.entity.coupon.CouponHistoryView;
import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.*;
import kr.co.ginong.web.entity.point.PointHistory;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.cart.CartService;
import kr.co.ginong.web.service.coupon.CouponService;
import kr.co.ginong.web.service.member.MemberService;
import kr.co.ginong.web.service.order.LocationService;
import kr.co.ginong.web.service.order.OrderService;
import kr.co.ginong.web.service.order.PaymentService;
import kr.co.ginong.web.service.point.PointService;
import kr.co.ginong.web.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("order")
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

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartService cartService;


    @GetMapping("info")
    public String info(Model model
            , @RequestParam(name = "p", required = false) Long prdId
            , @RequestParam(name = "q", required = false) Integer qty
            , HttpSession session
    ) {

        List<OrderItem> items = new ArrayList<>();

        //상품정보 가져오기
        //파라미터 없다면 세션에서 꺼내오기
        if(prdId != null && qty != null){
            OrderItem item = OrderItem.builder().productId(prdId).quantity(qty).build();
            items.add(item);
        }
        else
            items = (List<OrderItem>) session.getAttribute("orderItems");


        // 화면에 뿌려줄 주문 정보와 상품 정보 조합하기
        List<Map<String, Object>> list = new ArrayList<>();
        int totalPrice = 0;

        for (OrderItem i : items) {
            long pId = i.getProductId();
            ProductView p = productService.get(pId);

            //총상품값 계산해서 넣기
            int price = p.getPrice();
            int quantity = i.getQuantity();
            int total = price * quantity;
            totalPrice += total;

            i.setPrice(price);

            //주문 정보 + 상품 정보 List<Map>으로 만들기
            Map<String, Object> map = new HashMap<>();
            map.put("id", pId);
            map.put("name", p.getName() + ", " + p.getQuantity() + p.getQuantityCategory() + "(" + p.getWeight() + p.getWeightCategory() + ")");
            map.put("img", p.getThumbnailPath()+p.getThumbnailName());
            map.put("price", price);
            map.put("quantity", quantity+ "개");
            list.add(map);
        }


        //================================================================
        String name = "dmswls"; //로그인 구현 전이라 박아놓은 MEMBER_NAME

        Member member = memberService.get(name);
        Long mId = member.getId();


        //배송지정보 가져오기
        Long lId = (Long) session.getAttribute("locationId");

        Location location = new Location();
        if (lId == null)
            location = locationService.getByMemberID(mId);
        else
            location = locationService.getByID(lId);

        //모델 및 세션
        model.addAttribute("location", location);
        model.addAttribute("member", member);
        model.addAttribute("items", list);
        model.addAttribute("totalPrice", totalPrice);

        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("orderItems", items);
        session.setAttribute("orderItemsList", list);
        session.setAttribute("memberId", mId);

        return "user/order/info";
    }

    @PostMapping("info")
    public String info(
            LocationHistory locationHistory
            , HttpSession session
    ) {

        //세션에서 주문 목록 가져오기
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

        //주문 번호, location id order 테이블에 넣기
        Order order = Order.builder()
                .id(id)
                .categoryId(1L)
                .memberId(memberId)
                .locationId(locationHistory.getLocationId())
                .build();


        //주문 테이블 저장하기
        boolean vaild = service.add(order);

        // 주문 테이블 저장 성공 했을 때 주문 목록 저장 및 장바구니에서 삭제

        List<Long> cartList = new ArrayList<>();
        if (vaild){
            for (OrderItem i : items){
                i.setOrderId(id);
                cartList.add(i.getProductId());
            }
            service.addItems(items);
            cartService.delete(memberId, cartList);
        }

        //LOCATION_HISTORY 테이블에 넣기
        locationHistory.setCategoryId(1);
        locationHistory.setMemberId(memberId);
        locationHistory.setOrderId(id);

        locationService.addHistory(locationHistory);

        //세션에 주문 아이템 정보 업데이트
        session.setAttribute("orderItems", items);
        session.setAttribute("orderId", id);

        return "redirect:pay";
    }

    @GetMapping("pay")
    public String pay(
            HttpSession session
            , Model model
    ) {

        //세션에서 회원번호 및 화면에 뿌려줄 주문 정보 가져오기
        List<Map<String, Object>> items = (List<Map<String, Object>>) session.getAttribute("orderItemsList");
        int totalPrice = (int) session.getAttribute("totalPrice");
        Long memberId = (Long) session.getAttribute("memberId");

        // DB에서 사용가능한 쿠폰 리스트 가져오기
        List<CouponHistoryView> couponList = couponService.getAvailList(memberId);

        // 잔여 적립금 조회
        int point = pointService.getAvailPoint(memberId);

        // 모델
        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("point", point);
        model.addAttribute("couponList", couponList);
        return "user/order/pay";
    }

    @PostMapping("pay")
    public String pay(
            @RequestParam(name = "id", required = false) Long chid,
            @RequestParam(name = "cid", required = false) Long cid,
            @RequestParam(name = "usedAmt", required = false) Integer usedAmt
            , Payment payment
            , Integer point
            , HttpSession session
    ){

        //TODO 간편결제 API 구현 필요

        //session에서 주문 번호와 회원 번호 가져오기
        Long orderId = (Long) session.getAttribute("orderId");
        Long memberId = (Long) session.getAttribute("memberId");

        //결제정보 결제 테이블에 저장하기
        payment.setOrderId(orderId);
        payment.setMemberId(memberId);
        boolean pymt = paymentService.add(payment);

        //쿠폰 사용내역 업데이트
        boolean cupn = false;
        if(chid != null){
            CouponHistory couponHistory = CouponHistory.builder()
                    .id(chid)
                    .usedAmt(usedAmt)
                    .couponId(cid)
                    .orderId(orderId)
                    .memberId(memberId).build();
            cupn = couponService.updateHistory(couponHistory);
        }
        else
            cupn = true;


        //포인트 사용내역 저장
        boolean pnt = false;
        if(point != null && point >= 1000){
           PointHistory pointHistory
                   = PointHistory.builder()
                   .plma(-1)
                   .amount(point)
                   .memberId(memberId)
                   .orderId(orderId)
                   .build();
           pnt = pointService.addHistory(pointHistory);
        }
        else
            pnt = true;

        //결제금액 세션으로 넘기기
        int totalAmt = payment.getTotalAmt();
        session.setAttribute("totalAmt", totalAmt);

        if(pymt && pnt && cupn)
            return "redirect:complete";
        return null;
    }


    @GetMapping("complete")
    public String complete(HttpSession session, Model model) {

        //주문 정보 가져오기
        List<Map<String, Object>> items = (List<Map<String, Object>>) session.getAttribute("orderItemsList");
        Map<String, Object> item = items.get(0); //주문 목록 중 첫번째 상품
        int size = items.size()-1; //외 N건

        Long orderId = (Long) session.getAttribute("orderId");
        int totalAmt = (int) session.getAttribute("totalAmt");//총 결제금액


        // 사용자 이름 가져오기
        Long memberId = (Long) session.getAttribute("memberId");
        Member member = memberService.get(memberId);
        String name = member.getName();

        // 모델
        model.addAttribute("item", item);
        model.addAttribute("size", size);
        model.addAttribute("totalAmt", totalAmt);
        model.addAttribute("name", name);
        model.addAttribute("orderId", orderId);

        //세션에 있는 주문 관련 정보들 지우기
        session.removeAttribute("orderId");
        session.removeAttribute("totalPrice");
        session.removeAttribute("totalAmt");
        session.removeAttribute("orderItems");
        session.removeAttribute("orderItemsList");

        return "user/order/complete";
    }

    @GetMapping("list")
    public String list(Model model) {
        // 카테고리-주문내역 클릭 시, 사용자에게 시각효과를 보여주기 위한 상태값 전달
        model.addAttribute("active", "order");
        return "user/order/list";
    }

    @GetMapping("detail")
    public String detail() {
        return "user/order/detail";
    }

}
