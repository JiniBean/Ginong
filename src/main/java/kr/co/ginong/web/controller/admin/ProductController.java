package kr.co.ginong.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminPoductController")
@RequestMapping("admin/product")
public class ProductController {

    @GetMapping("list")
    public String list(){
        return "admin/product/list";
    }

    @GetMapping("reg")
    public String reg(){
        return "admin/product/reg";
    }

    @GetMapping("update")
    public String update(){
        return "admin/product/update";
    }

}