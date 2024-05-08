package kr.co.ginong.web.controller;

import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/index", "/home"})
public class IndexController {

    @Autowired
    private ProductService service;

    @GetMapping
    String index(Model model){
        List<ProductView> seasonOutList = service.getSeasonOutList();
        List<ProductView> commingSoonList = service.getCommingSoonList();

        model.addAttribute("seasonOutList", seasonOutList);
        model.addAttribute("commingSoonList", commingSoonList);

        // 데이터 확인 용
        // System.out.println("#####################################################################");
        // System.out.println("seasonOutList = " + seasonOutList);
        // System.out.println("seasonOutList.size() = " + seasonOutList.size());
        // System.out.println("#####################################################################");
        // System.out.println("seasonOutList = " + commingSoonList);
        // System.out.println("commingSoonList.size() = " + commingSoonList.size());
        // System.out.println("#####################################################################");

        model.addAttribute("seasonOutList", seasonOutList);
        model.addAttribute("commingSoonList", commingSoonList);

        return "index";
    }


}
