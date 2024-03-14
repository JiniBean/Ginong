package kr.co.ginong.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/product")
public class ProdcutCotroller {


    @GetMapping("detail")
    public String detail(){
        return "user/product/detail";
    }
}
