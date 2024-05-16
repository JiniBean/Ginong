package kr.co.ginong.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminOrderController")
@RequestMapping("admin/order")
public class OrderController {

    @GetMapping("list")
    public String list(){
        return "admin/order/list";
    }
}
