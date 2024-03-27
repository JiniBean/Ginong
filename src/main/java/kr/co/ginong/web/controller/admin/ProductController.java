package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;
import kr.co.ginong.web.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("adminProductController")
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("list")
    public String list(Model model){

        List<ProductView>list = new ArrayList<>();

        list = service.getList();

        model.addAttribute("list", list);
        return "admin/product/list";
    }

    @GetMapping("reg")
    public String save(){
        return "admin/product/reg";
    }
    @PostMapping("reg")
    public String save(Product product){
        //product 및 productCategory ... product img 를 받아와야 함 - 구조체 만들기

        service.save(product);

        return "redirect:admin/product/list";
    }

    @GetMapping("update")
    public String update(){

        return "admin/product/update";
    }

    @PostMapping("update")
    public String update(Product product){
        service.update(product);

        return "redirect:admin/product/list";
    }




}