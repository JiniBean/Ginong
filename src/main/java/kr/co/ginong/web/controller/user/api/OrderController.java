package kr.co.ginong.web.controller.user.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("{memberId}")
    public List<Order> getList(@PathVariable Long memberId) {

        List<Order> list = service.getListByMemberId(memberId);

        System.out.println(list);
        return list;
    }

    @GetMapping("{orderId}/items")
    public List<OrderItemView> getItems(@PathVariable Long orderId) {

        List<OrderItemView> itemList = service.getList(orderId);
        int totalPrice = 0;

        // List<OrderItemView> itemList = ...;
        // Map<Long, List<OrderItemView>> result = itemList.stream().collect(Collectors.groupingBy(OrderItemView::getOrderId));
//        Map<Long, List<OrderItemView>> result = new HashMap<>();
//        for (OrderItemView item : itemList) {
////            List<OrderItemView> orderItems = result.get(item.getOrderId());
////            if (orderItems == null) {
////                orderItems = new ArrayList<>();
////                result.put(item.getOrderId(), orderItems);
////            }
//            List<OrderItemView> orderItems = result.computeIfAbsent(item.getOrderId(), id -> new ArrayList<>());
//            orderItems.add(item);
//        }
        return itemList;
    }
    
    @GetMapping("{orderId}/location")
    public LocationHistory getLocation(@PathVariable Long orderId) {

        LocationHistory location = locationService.getByOrderID(orderId);
        return location;
    }

    @GetMapping("{orderId}/member")
    public Member getMember(@PathVariable Long orderId) {

        Member member = memberService.getByOrderId(orderId);
        return member;
    }

    @GetMapping("{orderId}/orderInfo")
    public OrderView getOrderInfo(@PathVariable Long orderId) {

        OrderView orderInfo = service.getOrderInfo(orderId);    
        return orderInfo;
    }

    @GetMapping("status")
    public List<OrderCategory> getStatus() {
        List<OrderCategory> categoryList = categoryService.getList();
        categoryList = categoryList.subList(0, 4);
        
        return categoryList;
    }

    @PutMapping("{orderId}")
    public void updateOrderType(@PathVariable Long orderId, @RequestBody int orderType) {
        System.out.println(orderId+", "+orderType);
    }

}
