package kr.co.ginong.web.controller;

import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/index", "/home"})
public class IndexController {

    @Autowired
    private ProductService service;

    @GetMapping
    //todo 베스트 / 특가 / 추천 기능 구현 필요
    String index(Model model) {
        List<ProductView> seasonOutList = service.getSeasonOutList();
        List<ProductView> commingSoonList = service.getCommingSoonList();

        model.addAttribute("seasonOutList", seasonOutList);
        model.addAttribute("commingSoonList", commingSoonList);

        return "index";
    }


}
