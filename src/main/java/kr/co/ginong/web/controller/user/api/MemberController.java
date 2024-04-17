package kr.co.ginong.web.controller.user.api;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("apiMemberController")
@RequestMapping("user/api/member")
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping("checkUserName")
    public Map<String,String> checkUserName(
            @RequestParam(name = "userName", required = false) String userName
    ){

        Map<String,String> map = new HashMap<>();

        Member member = service.get(userName);

        if(member!=null)
            map.put("name", member.getUserName());

        return map;
    }
}
