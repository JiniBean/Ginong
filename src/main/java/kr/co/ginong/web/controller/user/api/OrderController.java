package kr.co.ginong.web.controller.user.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.LocationHistory;
import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderCategory;
import kr.co.ginong.web.entity.order.OrderItemView;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.service.user.LocationService;
import kr.co.ginong.web.service.user.MemberService;
import kr.co.ginong.web.service.user.OrderCategoryService;
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

    @Autowired
    private OrderCategoryService categoryService;
    
    @GetMapping("list")
    public List<Order> getList() {
        Long memberId = null;
        memberId = Long.parseLong("40");

        List<Order> list = new ArrayList<>();
        list = service.getListByMemberId(memberId);

        System.out.println(list);
        return list;
    }

    @GetMapping("detail/items")
    public List<OrderItemView> getItems() {
       
        Long orderId = null;
        orderId = Long.parseLong("2024042490646637");

        List<OrderItemView> itemList = new ArrayList<>();
        itemList = service.getList(orderId);

        int totalPrice = 0;

        
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

    @GetMapping("detail/orderInfo")
    public OrderView getOrderInfo() {
        Long orderId = null;
        orderId = Long.parseLong("2024042490646637");

        OrderView orderInfo = service.getOrderInfo(orderId);    
        return orderInfo;
    }

    @GetMapping("detail/status")
    public List<OrderCategory> getStatus() {
        List<OrderCategory> categoryList =  categoryService.getList();
        categoryList = categoryList.subList(0, 4);
        
        return categoryList;
    } 

}
