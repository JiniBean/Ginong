package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("adminProductController")
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductService service;



    @GetMapping("list")
    public String list(Model model){

        List<ProductView>list = new ArrayList<>();

        list = service.getList(1);

        model.addAttribute("list", list);
        return "admin/product/list";
    }

    @GetMapping("reg")
    public String save(){
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
    public String update(@RequestParam(value = "id")Long productId, Model model){

        ProductView productView = service.get(productId);

        model.addAttribute("productView",productView);

        return "admin/product/update";
    }

    @PostMapping("update")
//    @PutMapping("{productId}")
    public String update(Product product){
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