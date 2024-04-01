package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @Autowired
    MemberService service;


}
