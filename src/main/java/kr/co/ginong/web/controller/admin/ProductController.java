package kr.co.ginong.web.controller.admin;

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
        // 쿠키에서 가져온 값을 Product 객체로 변환
        Product product = convertCookieToProduct(productCookie);
        // Model에 Product 객체 추가
        model.addAttribute("prd", product);
        return "admin/product/reg";
    }

    // 쿠키에서 가져온 값을 Product 객체로 변환하는 메서드
    private Product convertCookieToProduct(String productCookie) {
        // 쿠키에 저장된 값이 없는 경우 기본값으로 Product 객체 생성
        if (productCookie == null || productCookie.isEmpty()) {
            return new Product();
        }

        // 쿠키에서 가져온 JSON 문자열을 Product 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 쿠키에서 가져온 JSON 문자열을 Product 배열로 변환
            Product[] products = objectMapper.readValue(productCookie, Product[].class);
            // 배열의 첫 번째 원소를 반환 (임시저장 기능이므로 항상 하나의 상품만 저장될 것으로 가정)
            return products[0];
        } catch (IOException e) {
            e.printStackTrace();
            // JSON 파싱에 실패한 경우 기본값으로 Product 객체 생성
            return new Product();
        }
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