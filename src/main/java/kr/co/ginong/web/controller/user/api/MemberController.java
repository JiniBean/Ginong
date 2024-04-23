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
    public ResponseEntity<Boolean> add(@RequestBody Map<String, String> params) {

        Long memberId = null;

        //회원등록
        {
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

            memberId = service.addMember(member);

            if (memberId == null)
                return ResponseEntity.badRequest().body(false);

        }

        //가입경로 등록
        {
            String joinRoute = params.get("joinRoute");

            boolean isValid =false;

            //가입경로 선택했다면 등록
            if(!joinRoute.equals("default")){

                //실패시 false
                isValid = service.addRoute(memberId,joinRoute);

                if(!isValid)
                    return ResponseEntity.badRequest().body(false);

            }

        }

        return ResponseEntity.ok(true);

    }


}
