package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.service.user.MemberService;
import kr.co.ginong.web.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import kr.co.ginong.web.entity.member.Member;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @GetMapping("info")
    public String info(Model model){

        String name ="dmswls";

        Member member =  memberService.getMemberInfo(name);
        model.addAttribute("memberList", member);

        //배송지정보 가져오기
        Location location = memberService.getLocation(member.getId());
        model.addAttribute("location",location);

        return "user/order/info";
    }

    @GetMapping("pay")
    public String pay() {

        return "user/order/pay";
    }


}
