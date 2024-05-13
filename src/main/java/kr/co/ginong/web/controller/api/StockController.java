package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.entity.product.StockView;
import kr.co.ginong.web.service.user.CartService;
import kr.co.ginong.web.service.user.LocationService;
import kr.co.ginong.web.service.user.ProductService;
import kr.co.ginong.web.service.user.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminStockController")
@RequestMapping("api/stock")
public class StockController {

    @Autowired
    StockService service;

    @GetMapping
    public List<StockView> list(@RequestParam(name = "a" ,required = false) Boolean amount,
                                @RequestParam(name = "c" ,required = false) Boolean current,
                                @RequestParam(name = "q" ,required = false) String query){

        List<StockView> list = service.getList(query, amount,current);

       return list;
    }

    @GetMapping("/{prdId}")
    public List<StockView> list(@PathVariable Long prdId){

        List<StockView> stock = service.getByPrdId(prdId);

        return stock;
    }


}
