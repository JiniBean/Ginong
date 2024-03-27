package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("info")
    public String info(){

        return "user/order/info";
    }

    @GetMapping("pay")
    public String pay() {

        return "user/order/pay";
    }

    @GetMapping("complete")
    public String complete() {
        return "user/order/complete";
    }

}
