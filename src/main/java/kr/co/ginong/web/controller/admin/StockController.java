package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.product.*;
import kr.co.ginong.web.service.user.ProductService;
import kr.co.ginong.web.service.user.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller("stockController")
@RequestMapping("admin/stock")
public class StockController {

    @Autowired
    StockService service;

    @Autowired
    ProductService productService;

    @GetMapping("list")
    public String list(){

        return "admin/stock/list";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(name = "p") Long prdId
            , Model model){
        List<StockView> list = service.getByPrdId(prdId);
        ProductView prd = productService.get(prdId);

        model.addAttribute("list", list);
        model.addAttribute("prd", prd);
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
    public String reg(Stock stock
                    ,@AuthenticationPrincipal WebUserDetails userDetails){


        Long memberId = 0L;

        // 사용자가 인증되었는지 확인
        if (userDetails != null) {
            memberId = userDetails.getId(); //사용하고 싶은 정보 담기
        }
        stock.setMemberId(memberId);

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

    @PostMapping("update")
    public String update(Stock stock
            ,@AuthenticationPrincipal WebUserDetails userDetails){


        Long memberId = 0L;

        // 사용자가 인증되었는지 확인
        if (userDetails == null) {
            return "/signup";
        }
        memberId = userDetails.getId(); //사용하고 싶은 정보 담기
        stock.setMemberId(memberId);

        boolean valid = service.edit(stock);

        if (!valid)
            return "redirect:update?id="+stock.getId();
        return "redirect:detail?p="+stock.getProductId();
    }

    @PostMapping("delete")
    public String delete(@RequestParam(name="id") List<Long> ids,
                         @RequestParam(name="prdId") Long prdId){

        System.out.println(ids.toString());
        System.out.println(prdId);
        Boolean valid = service.delete(ids);

        return "redirect:detail?p="+prdId;

    }

}
