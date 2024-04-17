package kr.co.ginong.web.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("adminProductController")
@RequestMapping("admin/product")

public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("list")
    public String list(@RequestParam(name = "q", required = false) String query
            , @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
            , Model model) {

        List<ProductView> list = new ArrayList<>();
        int count = 0;

        if (query != null) {
            list = service.getList(page, query);
            count = service.count(query);
        } else {
            list = service.getList(page);
            count = service.count();
        }

        model.addAttribute("count", count);
        model.addAttribute("list", list);
        return "admin/product/list";
    }

    @PostMapping("list")
    public String list(@RequestParam("state") List<Long> ids) {

        service.hidden(ids);

        return "redirect:/admin/product/list";
    }

    @GetMapping("reg")
    public String save(@CookieValue(name = "product", required = false) String productCookie, Model model) {

        ObjectMapper objectMapper = new ObjectMapper();
        Product product = new Product();

        try {
            Product[] products = objectMapper.readValue(productCookie, Product[].class);
            product = products[0];
        } catch (IllegalArgumentException | JsonProcessingException e) {
            // IllegalArgumentException과 JsonProcessingException 모두 이곳에서 처리
            // IllegalArgumentException => 전달되는 arguments가 null일 경우
            // JsonProcessingException => JSON Parsing하는 과정에서 에러 발생할 경우
            e.printStackTrace();
        }

        model.addAttribute("prd", product);

        return "admin/product/reg";
    }

    @PostMapping("reg")
    public String save(Product product
            , @RequestParam("img-file") MultipartFile imgFile) {
        // , Date madeDate
        // , String amount){
        //product 및 productCategory ... product img 를 받아와야 함 - 구조체 만들기
        /*product 데이터 넣는 곳*/
        String imgName = imgFile.getOriginalFilename();

        product.setAdminId(1);
        product.setThumbnailName(imgName);
        product.setThumbnailPath("/admin");
        System.out.println(product);

        service.save(product);//, madeDate, amount);


        return "redirect:list";
    }

    @GetMapping("update")
    public String update(@RequestParam(value = "id") Long productId, Model model) {

        ProductView productView = service.get(productId);

        model.addAttribute("productView", productView);

        return "admin/product/update";
    }

    @PostMapping("update")
//    @PutMapping("{productId}")
    public String update(Product product) {
        service.update(product);
        return "redirect:/admin/product/list";
    }

    @PostMapping("delete")
//    @DeleteMapping("/{productId}")
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/admin/product/list";
    }

}