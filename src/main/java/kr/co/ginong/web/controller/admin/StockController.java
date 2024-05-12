package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.product.*;
import kr.co.ginong.web.service.user.ProductService;
import kr.co.ginong.web.service.user.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @GetMapping("detail")
    public String detail(@RequestParam(name = "p") Long prdId
            , Model model){
        List<StockView> list = service.getByPrdId(prdId);

        model.addAttribute("list", list);
        return "admin/stock/detail";
    }

    @GetMapping("reg")
    public String reg(@RequestParam(name = "p") Long prdId , Model model){

        List<StockView> list = service.getByPrdId(prdId);
        StockView view =  list.get(0);
        List<StockCategory> categories = service.getCategories();

        model.addAttribute("view", view);
        model.addAttribute("categories", categories);
        return "admin/stock/reg";
    }

    @PostMapping("reg")
    public String reg(Stock stock,
                      @RequestParam(name ="io") String io,
                      @RequestParam(name = "made") String made
                     ){

//        @AuthenticationPrincipal WebUserDetails userDetails
//        Long memebrId = userDetails.getId();

        Long memebrId = 2L;
        stock.setMemberId(memebrId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            stock.setIoDate(dateFormat.parse(io));
            stock.setMadeDate(dateFormat.parse(made));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        boolean valid = service.add(stock);
        if (!valid)
            return "redirect:reg?p="+stock.getProductId();
        return "redirect:detail?p="+stock.getProductId();
    }

    @GetMapping("update")
    public String update(@RequestParam(name = "id") Long id , Model model){

        StockView stock = service.getById(id);
        model.addAttribute("view", stock);

        return "admin/stock/update";
    }

}
