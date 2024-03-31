package kr.co.ginong.web.controller.admin;

import kr.co.ginong.web.entity.Product;
import kr.co.ginong.web.entity.ProductView;
import kr.co.ginong.web.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("adminProductController")
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("list")
    public String list(Model model){

        List<ProductView>list = new ArrayList<>();

        list = service.getList();

        model.addAttribute("list", list);
        return "admin/product/list";
    }

    @GetMapping("reg")
    public String save(){
        return "admin/product/reg";
    }
    @PostMapping("reg")
    public String save(
                        Product product,
                        @RequestParam("img-file") MultipartFile imgFile,
                        String categoryId,
                        String madeDate,
                        String amount
                        ){
        //product 및 productCategory ... product img 를 받아와야 함 - 구조체 만들기


        String imgName = imgFile.getOriginalFilename();
        System.out.println(categoryId);
        System.out.println(madeDate);
        System.out.println(amount);
        System.out.println(imgName);

        product.setThumbnailPath("/admin");
        product.setAdminId(1);
        product.setThumbnailName(imgName);
        System.out.println(product);

        service.save(product);
        return "redirect:list";
    }

    @GetMapping("update")
    public String update(@RequestParam(value = "id")Long productId, Model model){

        ProductView productView = service.get(productId);

        model.addAttribute("productView",productView);

        return "admin/product/update";
    }

    @PostMapping("update")
    public String update(Product product){
        service.update(product);
        return "redirect:admin/product/list";
    }

}