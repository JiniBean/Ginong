package kr.co.ginong.web.controller.user.api;

import kr.co.ginong.web.entity.product.ProductView;
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
            , @RequestParam(name = "s", required = false) Integer sortType
    ) {

        List<ProductView> list = new ArrayList<>();

        int count = 0;

        if (categoryId != null && query != null) {
            list = service.getList(page, categoryId, query);
            count = service.count(categoryId, query);
        } else if (categoryId != null && sortType != null) {
            list = service.getList(page, categoryId, sortType);
            count = service.count(categoryId);
        } else if (categoryId != null) {
            list = service.getList(page, categoryId);
            count = service.count(categoryId);
        } else if (query != null && sortType != null) {
            list = service.getList(page, query, sortType);
            count = service.count(query);
        } else if (query != null) {
            list = service.getList(page, query);
            count = service.count(query);
        } else if (sortType != null) {
            list = service.getList(page, sortType);
            count = service.count();
        } else {
            list = service.getList(page);
            count = service.count();
        }

        return list;
    }


}
