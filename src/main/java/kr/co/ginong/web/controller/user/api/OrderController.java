package kr.co.ginong.web.controller.user.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.service.user.MemberService;
import kr.co.ginong.web.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiOrderController")
@RequestMapping("user/api/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private MemberService memberService;

    


}
