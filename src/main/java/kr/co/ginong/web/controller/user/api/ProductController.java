package kr.co.ginong.web.controller.user.api;

import kr.co.ginong.web.entity.ProductView;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    ) {

        List<ProductView> list = new ArrayList<>();

        int count = 0;

        if (categoryId != null && query != null) {
            list = service.getList(page, categoryId, query);
        } else if (categoryId != null) {
            list = service.getList(page, categoryId);
        } else if (query != null) {
            list = service.getList(page, query);
        } else {
            list = service.getList(page);
        }

        return list;
    }


}
