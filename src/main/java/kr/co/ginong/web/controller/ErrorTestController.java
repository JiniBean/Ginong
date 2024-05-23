package kr.co.ginong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorTestController {


    // 테스트 페이지가 화면에 실제로 어떻게 출력되는지 확인을 위한 Test Controller 입니다.
    // 추후 배포시 삭제해도 무방합니다.

    // 주문처리 실패
//    @GetMapping("/100")
//    public String trigger100() {
//        return "/error/order-failed";
//    }

    @GetMapping("/400")
    public String trigger400() {
        return "/error/400";
    }

    @GetMapping("/401")
    public String trigger401() {
        return "/error/401";
    }

    @GetMapping("/403")
    public String trigger403() {
        return "/error/403";
    }

    @GetMapping("/404")
    public String trigger404() {
        return "/error/404";
    }

    @GetMapping("/500")
    public String trigger500() {
        return "/error/500";
    }



}
