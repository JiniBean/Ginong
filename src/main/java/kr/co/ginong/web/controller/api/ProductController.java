package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("apiProductController")
@RequestMapping("user/api/product")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public List<ProductView> list(
            @RequestParam(name = "c", required = false) Long categoryId
            , @RequestParam(name = "q", required = false) String query
            , @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
            , @RequestParam(name = "s", required = false) Integer sortType
            , @RequestParam(name = "r", required = false) Integer rows
    ) {

        List<ProductView> list = new ArrayList<>();

        int count = 0;
        if(rows == null) rows = 20;

        list = service.getList(page, categoryId, query, sortType, rows);
        count = service.count(categoryId, query);

        return list;
    }

    @PostMapping("cart-list")
    public List<Map<String, Object>> cartList(@RequestBody List<Long> ids){
        return service.getCartList(ids);
    }


}
