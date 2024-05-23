package kr.co.ginong.web.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ginong.web.entity.product.Product;
import kr.co.ginong.web.entity.product.ProductImg;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.admin.ProductImgService;
import kr.co.ginong.web.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("adminProductController")
@RequestMapping("admin/product")

public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductImgService imgService;

    @GetMapping("list")
    public String list(@RequestParam(name = "q", required = false) String query
            , @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
            , Model model) {

        List<ProductView> list = new ArrayList<>();
        int count = 0;

        if (query != null) {
            list = service.getListforAdmin(page, query);
            count = service.getCount(query);
        } else {
            list = service.getListforAdmin(page);
            count = service.getCount();
        }

        model.addAttribute("count", count);
        model.addAttribute("list", list);
        model.addAttribute("active", "product");

        String pageName="상품관리";
        model.addAttribute("pageName", pageName);

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

        String pageName="상품등록";
        model.addAttribute("pageName", pageName);

        return "admin/product/reg";
    }

    @PostMapping("reg")
    public String save(Product product
            , @RequestParam("img-file") List<MultipartFile> imgFile
            , HttpServletRequest request) throws IOException {
        // , Date madeDate
        // , String amount){
        //product 및 productCategory ... product img 를 받아와야 함 - 구조체 만들기
        /*product 데이터 넣는 곳*/

        // 상품 등록 시 추가한 이미지 리스트
        String imgName = imgFile.get(0).getOriginalFilename();

        // 썸네일 저장
        product.setMemberId(1L);
        product.setThumbnailName(imgName);
        product.setThumbnailPath("/img");
        
        service.save(product);//, madeDate, amount)

        for (int i=0; i<imgFile.size(); i++) {
            if (!imgFile.get(i).isEmpty()) {
                imgName = imgFile.get(i).getOriginalFilename();
                
                String path = "/img";
                String realPath = request.getServletContext().getRealPath(path);

                System.out.println(realPath);
                
                File file = new File(realPath);
                if(!file.exists())
                    file.mkdirs();              

                File filePath = new File(realPath+File.separator+imgName);                
                imgFile.get(i).transferTo(filePath);

                ProductImg productImg = ProductImg
                                        .builder()
                                        .productId(product.getId())
                                        .imgPath(path)
                                        .name(imgName).build();

                imgService.add(productImg);
            }
        }

        return "redirect:list";
    }

    @GetMapping("update")
    public String update(@RequestParam(value = "id") Long productId, Model model) {

        ProductView productView = service.get(productId);

        model.addAttribute("productView", productView);

        String pageName="상품수정";
        model.addAttribute("pageName", pageName);

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