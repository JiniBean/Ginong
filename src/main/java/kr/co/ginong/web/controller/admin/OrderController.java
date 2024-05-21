package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.LocationHistory;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.entity.order.Payment;
import kr.co.ginong.web.service.member.MemberService;
import kr.co.ginong.web.service.order.LocationService;
import kr.co.ginong.web.service.order.OrderService;
import kr.co.ginong.web.service.order.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("adminOrderController")
@RequestMapping("admin/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private MemberService memberService;

    @GetMapping("list")
    public String list(Model model){

        String pageName="주문 관리";

        model.addAttribute("pageName", pageName);
        return "admin/order/list";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "e", required = false) Long exchangeId,
                         @RequestParam(name = "r", required = false) Long refundId,
                         @RequestParam(name = "c", required = false) Long cancelId,
                         Model model){

        List<OrderView> list = service.getItems(id);
        Payment payment = paymentService.getByOrderId(id);
        LocationHistory locationHistory = locationService.getHistoryByOrderID(id);
        Member member = memberService.getByOrderId(id);

        OrderView exchange = null;
        OrderView refund = null;
        List<OrderView> cancel = null;

        int totalPrice = 0;
        int totalQty =0;

        for (OrderView o :list){
            totalQty += o.getQuantity();
            totalPrice += o.getQuantity()*o.getPrice();
        }

        String pageName="주문 상세";

        if(exchangeId != null)
            exchange = service.getByExchangeId(exchangeId);
        if(refundId != null)
            refund = service.getByRefundId(refundId);
        if(cancelId != null)
            cancel = service.getItemsByCancelId(cancelId);




        model.addAttribute("pageName", pageName);
        model.addAttribute("list", list);
        model.addAttribute("payment", payment);
        model.addAttribute("locationHistory", locationHistory);
        model.addAttribute("member", member);
        model.addAttribute("exchange", exchange);
        model.addAttribute("refund", refund);
        model.addAttribute("cancel", cancel);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalQty", totalQty);
        return "admin/order/detail";
    }
}
