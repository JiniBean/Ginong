package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.entity.mypage.ReviewView;
import kr.co.ginong.web.entity.product.ProductCategory;
import kr.co.ginong.web.entity.product.ProductQna;
import kr.co.ginong.web.entity.product.ProductQnaView;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.ProductCategoryService;
import kr.co.ginong.web.service.user.ProductService;
import kr.co.ginong.web.service.user.QnaService;
import kr.co.ginong.web.service.user.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private QnaService qnaService;

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
    public String detail(@RequestParam(value = "id") Long productId
                         ,Model model) {

        productId = 11L;

        ProductView productView = service.get(productId);

        List<ReviewView> reviewView = reviewService.getProductReviews(productId);

        List<ProductQnaView> qnaView= qnaService.getProductQna(productId);



//        List<ReviewView> reviewView = reviewService.getMemberReviews(memberId);

        System.out.println("reviewView =" + reviewView.toString());

        System.out.println("id = " + productId);

        model.addAttribute("productView", productView);

        model.addAttribute("reviewView", reviewView);

//        model.addAttribute("reviewView", reviewView);



        return "user/product/detail";
    }



//    @PostMapping("detail")
//    public String detail(Model model){
//
//        List<ProductView> list = new ArrayList<>();
//
//
//
//        model.addAttribute("list", list);
//
//        if (Value == "product_id"){
//            return "redirect:user/order/info";
//        }
//        else {
//            return "redirect:user/cart";
//        }
//
//    }


}
