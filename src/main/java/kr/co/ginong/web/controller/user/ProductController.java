package kr.co.ginong.web.controller.user;

import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.entity.mypage.ReviewView;
import kr.co.ginong.web.entity.product.ProductCategory;
import kr.co.ginong.web.entity.product.ProductQnaView;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private QnaService qnaService;

    @Autowired
    private CartService cartService;

    @GetMapping("list")
    public String list(
            @RequestParam(name = "c", required = false) Long categoryId
            , @RequestParam(name = "q", required = false) String query
            , @RequestParam(name = "p", defaultValue = "1") Integer page
            , @RequestParam(name = "s", required = false) Integer sortType
            , @CookieValue(name = "cartList", required = false) List<Cart> carts
            , @AuthenticationPrincipal WebUserDetails userDetails
            , Model model) {

        List<ProductView> prds = new ArrayList<>();
        List<ProductCategory> categories = categoryService.getList();

        int count = 0;

        prds = service.getList(page, categoryId, query, sortType);
        count = service.count(categoryId, query);

        // 장바구니에 담겨있는 상품 체크
        List<Cart> cartList = new ArrayList<>();
        // 임시로 박아 놓은 회원 번호임 로그인 완성 후 삭제 예정
        Long memberId = null;

        // 로그인 했다면
        if(userDetails != null){
            memberId = userDetails.getId();
            cartList = cartService.getList(memberId);
        }
        // 로그인 안했지만 장바구니 목록이 있다면
        else if (carts != null)
            cartList = carts;


        // 화면에 뿌려줄 상품 정보와 장바구니 정보 조합하기
        List<Map<String, Object>> list = new ArrayList<>();

        for (ProductView p  : prds) {

            //상품 정보 List<Map>으로 만들기
            Map<String, Object> map = new HashMap<>();
            map.put("id", p.getId());
            map.put("name", p.getName());
            map.put("quantity", p.getQuantity());
            map.put("quantityCategory", p.getQuantityCategory());
            map.put("weight", p.getWeight());
            map.put("weightCategory", p.getWeightCategory());
            map.put("price", p.getPrice());
            map.put("likeCount", p.getLikeCount());
            map.put("thumbnailPath", p.getThumbnailPath());

            // 담은 개수 초기화
            map.put("cartQty", null);

            // 장바구니에 담긴게 있다면 넣어주기
            for (Cart c : cartList)
                if (p.getId() == c.getProductId())
                    map.put("cartQty", c.getQuantity());

            list.add(map);
        }

        model.addAttribute("categories", categories);
        model.addAttribute("count", count);
        model.addAttribute("list", list);

        return "user/product/list";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(value = "id") Long productId
                         ,Model model) {

        ProductView productView = service.get(productId);
        List<ReviewView> reviewView = reviewService.getProductReviews(productId);
        List<ProductQnaView> qnaView= qnaService.getProductQna(productId);

        model.addAttribute("productView", productView);
        model.addAttribute("reviewView", reviewView);
        model.addAttribute("qnaView", qnaView);

        return "user/product/detail";
    }
    @GetMapping("recent")
    public String list() {
        return "user/recent/list";
    }

}
