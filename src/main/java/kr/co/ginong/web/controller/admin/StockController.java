package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.entity.product.StockView;
import kr.co.ginong.web.service.user.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("stockController")
@RequestMapping("admin/stock")
public class StockController {

    @Autowired
    StockService service;
    @GetMapping("list")
    public String list(){

        return "admin/stock/list";
    }

}
