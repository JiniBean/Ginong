package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.entity.product.ProductCategory;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.ProductCategoryService;
import kr.co.ginong.web.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("list")
    public String list(
            @RequestParam(name = "c", required = false) Long categoryId
            , @RequestParam(name = "q", required = false) String query
            , @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
            , Model model) {

        List<ProductView> list = new ArrayList<>();
        List<ProductCategory> categories = categoryService.getList();

        int count = 0;

        if (categoryId != null && query != null) {
            list = service.getList(page, categoryId, query);
            count = service.count(categoryId, query);
        } else if (categoryId != null) {
            list = service.getList(page, categoryId);
            count = service.count(categoryId);
        } else if (query != null) {
            list = service.getList(page, query);
            count = service.count(query);
        } else {
            list = service.getList(page);
            count = service.count();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("count", count);
        model.addAttribute("list", list);

        return "user/product/list";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(value = "id") Long productId, Model model) {
        // 배송비 및 신선해요 태그 등 product 테이블에 없는 정보가 있어 view 필요

        ProductView productView = service.get(productId);
        model.addAttribute("productView", productView);



        return "user/product/detail";
    }
}
