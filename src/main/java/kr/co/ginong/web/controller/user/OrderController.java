package kr.co.ginong.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/order")
public class OrderController {

    @GetMapping("info")
    public String info(){
        return "user/order/info";
    }


    @GetMapping("pay")
    public String pay() { return "user/order/pay"; }
}
