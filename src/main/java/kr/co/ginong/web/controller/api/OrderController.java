package kr.co.ginong.web.controller.api;

import java.util.List;

import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.order.*;
import kr.co.ginong.web.service.order.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import kr.co.ginong.web.service.order.LocationService;
import kr.co.ginong.web.service.order.OrderService;

import static java.util.stream.Collectors.groupingBy;

@RestController("apiOrderController")
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private  LocationService locationService;

    @GetMapping()
    public List<OrderView> list(@RequestParam(name = "q", required = false) String query,
                                   @RequestParam(name = "s", required = false) Boolean sort,
                                   @RequestParam(name = "u", required = false, defaultValue = "false") Boolean isUser,
                                   @AuthenticationPrincipal WebUserDetails userDetails) {
        Long memberId = null;
        if(isUser && userDetails!=null)
            memberId = userDetails.getId();

        List<OrderView> list = service.getList(memberId,query,sort);
        System.out.println("======================");
        System.out.println(list);
        System.out.println("======================");
        return service.getList(memberId,query,sort);
    }

    @GetMapping("cncl")
    public List<OrderView> cancelList(@RequestParam(name = "q", required = false) String query,
                                         @RequestParam(name = "u", required = false, defaultValue = "false") Boolean isUser,
                                         @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(isUser && userDetails!=null)
            memberId = userDetails.getId();

        return service.getCancelList(memberId,query);
    }

    @GetMapping("exrf")
    public List<OrderView> exRefList(@RequestParam(name = "q", required = false) String query,
                                        @RequestParam(name = "s", required = false) Integer sort,
                                        @RequestParam(name = "e", required = false) Boolean isEx,
                                        @RequestParam(name = "r", required = false) Boolean isRef,
                                        @RequestParam(name = "u", required = false, defaultValue = "false") Boolean isUser,
                                        @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(isUser && userDetails!=null)
            memberId = userDetails.getId();

        return service.getExRefList(memberId,query,sort,isEx, isRef);
    }

    @GetMapping("all")
    public List<OrderView> allCancelList(@RequestParam(name = "q", required = false) String query,
                                            @RequestParam(name = "u", required = false, defaultValue = "false") Boolean isUser,
                                            @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(isUser && userDetails!=null)
            memberId = userDetails.getId();

        return service.getAllCancelist(memberId,query);
    }

    @GetMapping("{orderId}")
    public List<OrderView> items(@PathVariable Long orderId){

        return service.getItems(orderId);
    }

    @GetMapping("c")
    public List<OrderCategory> categories() {
        List<OrderCategory> categoryList = service.getCategories();

        return categoryList;
    }

    @GetMapping("p")
    public Payment payment(@RequestParam(name = "o") Long orderId){

        return paymentService.getByOrderId(orderId);
    }

    @GetMapping("l")
    public LocationHistory locationHistory(@RequestParam(name = "o") Long orderId){
        return locationService.getByOrderID(orderId);
    }

    @GetMapping("delivery-status")
    public List<OrderCategory> getDeliveryStatus() {
        List<OrderCategory> categoryList = service.getCategories();
        categoryList = categoryList.subList(0, 4);
        
        return categoryList;
    }

    @PostMapping("/u")
    public Boolean update(@RequestBody Order order) {

        return service.edit(order);
    }

}
