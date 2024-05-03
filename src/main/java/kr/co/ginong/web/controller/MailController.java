package kr.co.ginong.web.controller;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.service.MailService;
import kr.co.ginong.web.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class MailController {

    @Autowired
    MailService service;

    @Autowired
    MemberService memberService;
    private int number; // 이메일 확인을 위한 번호


    // 이메일을 보내는 메서드
    @ResponseBody
    @PostMapping("/mailSend")
    public ResponseEntity<HashMap<String, Object>> mailSend(Member member, Model model) {
        String email = member.getEmail();
        String userName = member.getUserName();
        String name = member.getName();

        HashMap<String, Object> map = new HashMap<>();

        int valid = memberService.search(email, userName);

        Member validId = memberService.searchId(email, name);

        if (valid == 1 || validId != null) {
            // MailService를 사용하여 입력된 이메일(mail)을 전송하고,
            // 성공 시 발급된 번호를 number에 저장
            number = service.sendMail(email);

            // 성공적으로 이메일을 보냈다는 메시지와 함께 성공 응답 전송
            map.put("success", Boolean.TRUE);

            if (validId != null) {

                map.put("userName", validId.getUserName());
                map.put("joinDate", validId.getJoinDate());
                model.addAttribute("validId", validId); // validId를 모델에 추가
            }

        } else {
            map.put("success", Boolean.FALSE);
        }

        return ResponseEntity.ok(map);
    }

    @ResponseBody
    @PostMapping("/emailSend")
    public ResponseEntity<HashMap<String, Object>> emailSend(Member member
            , Model model){
        String email = member.getEmail();

        HashMap<String, Object> map = new HashMap<>();
        // MailService를 사용하여 입력된 이메일(mail)을 전송하고,
        // 성공 시 발급된 번호를 number에 저장
        number = service.sendMail(email);
        // 성공적으로 이메일을 보냈다는 메시지와 함께 성공 응답 전송
        map.put("success", Boolean.TRUE);

        return ResponseEntity.ok(map);
    }

    // 메일 확인을 위한 메서드
    @GetMapping("/mailCheck")
    public ResponseEntity<HashMap<String, Object>> mailCheck(@RequestParam int userNumber){
        HashMap<String, Object> map = new HashMap<>();
        boolean isMatch = userNumber == number; // 입력된 번호와 발급된 번호 비교

        // 사용자가 입력한 번호와 발급된 번호가 일치하는지 확인
        if (isMatch) {
            // 일치할 경우 일치 메시지와 함께 성공 응답 전송
            map.put("success", Boolean.TRUE);
            map.put("message", "일치합니다.");
        } else {
            // 불일치할 경우 에러 메시지와 함께 실패 응답 전송
            map.put("success", Boolean.FALSE);
            map.put("error", "일치하지 않습니다.");
        }

        return ResponseEntity.ok(map);
    }
}
