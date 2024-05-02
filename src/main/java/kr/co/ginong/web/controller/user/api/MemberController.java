package kr.co.ginong.web.controller.user.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kr.co.ginong.web.dto.Signup;
import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("apiMemberController")
@RequestMapping("user/api/member")
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping("checkUserName")
    public ResponseEntity<Boolean> checkUserName(
            @RequestParam(name = "userName", required = false) String userName
    ) {

        Member member = service.get(userName);

        if (member!= null)
            return ResponseEntity.ok(true); //아이디 있다

        return ResponseEntity.badRequest().body(false); //아이디 없다

    }

    @PostMapping("add")
    public ResponseEntity<Boolean> add(
            @RequestBody String params
    ) {

        System.out.println(params);

        if(params.isBlank())
            return ResponseEntity.badRequest().body(false);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(params, JsonObject.class);

        // "userInfo" 키에 해당하는 값을 추출하여 JsonArray 객체로 변환
        JsonArray userInfoArray = jsonObject.getAsJsonArray("userInfo");

        System.out.println(userInfoArray);

        //step1
        jsonObject = (JsonObject) userInfoArray.get(0);
        String agreeStr = String.valueOf(jsonObject.get("agree")).replace("\"", "");

        boolean agree=false;

        if(agreeStr.equals("Y"))
            agree=true;

        //step2
        jsonObject = (JsonObject) userInfoArray.get(1);
        String name = String.valueOf(jsonObject.get("name")).replace("\"", "");
        String email = String.valueOf(jsonObject.get("email")).replace("\"", "");
        String phone = String.valueOf(jsonObject.get("phone")).replace("\"", "");

        //step3
        jsonObject = (JsonObject) userInfoArray.get(2);
        String userName = String.valueOf(jsonObject.get("userName")).replace("\"", "");
        String pwd = String.valueOf(jsonObject.get("pwd")).replace("\"", "");
        String joinRoute = String.valueOf(jsonObject.get("joinRoute")).replace("\"", "");

        Long memberId = null;

        //회원등록
        {

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
