package kr.co.ginong.web.controller.user.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ginong.web.entity.order.OrderItemView;
import kr.co.ginong.web.service.user.MemberService;
import kr.co.ginong.web.service.user.OrderService;

@RestController("apiOrderController")
@RequestMapping("user/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private MemberService memberService;

    @GetMapping("detail")
    public List<OrderItemView> list() {
        Long orderId = null;
        List<OrderItemView> itemList = new ArrayList<>();
        
        orderId = Long.parseLong("2024042490646637");
        itemList = service.getList(orderId);

        return itemList;
    }    

}
