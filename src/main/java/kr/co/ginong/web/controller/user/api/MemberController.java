package kr.co.ginong.web.controller.user.api;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

    @PostMapping("add")
    public ResponseEntity<Boolean> add(@RequestBody Map<String, String> params){

        System.out.println("stepinfo="+params);

        String name = params.get("name");
        String userName = params.get("userName");
        String pwd = params.get("pwd");
        String email = params.get("email");
        String phone = params.get("phone");
        boolean agree = Boolean.parseBoolean(params.get("agree"));

        Member member = Member.builder()
                            .name(name)
                            .userName(userName)
                            .pwd(pwd)
                            .email(email)
                            .phone(phone)
                            .agree(agree)
                            .build();

        System.out.println(member.toString());

        boolean result = service.addMember(member);

        if(result)
            return ResponseEntity.ok(true);

        return ResponseEntity.badRequest().body(false);
    }



}
