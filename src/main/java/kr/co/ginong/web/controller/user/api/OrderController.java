package kr.co.ginong.web.controller.user.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.LocationHistory;
import kr.co.ginong.web.entity.order.OrderItemView;
import kr.co.ginong.web.service.user.LocationService;
import kr.co.ginong.web.service.user.MemberService;
import kr.co.ginong.web.service.user.OrderService;

@RestController("apiOrderController")
@RequestMapping("user/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private MemberService memberService;

    @Autowired
    private LocationService locationService;
    

    @GetMapping("detail")
    public List<OrderItemView> getItems() {
       
        Long orderId = null;
        orderId = Long.parseLong("2024042490646637");

        List<OrderItemView> itemList = new ArrayList<>();
        itemList = service.getList(orderId);

        return itemList;
    }
    
    @GetMapping("detail/location")
    public LocationHistory getLocation() {
        Long orderId = null;
        orderId = Long.parseLong("2024042490646637");

        LocationHistory location = locationService.getByOrderID(orderId);
        return location;
    }

    @GetMapping("detail/member")
    public Member getMember() {
        Long orderId = null;
        orderId = Long.parseLong("2024042490646637");

        Member member = memberService.getByOrderId(orderId);
        return member;
    }

}
