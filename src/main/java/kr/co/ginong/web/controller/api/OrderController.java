package kr.co.ginong.web.controller.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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

    @GetMapping("exch")
    public List<OrderView> exchangeList(@RequestParam(name = "q", required = false) String query,
                                        @RequestParam(name = "s", required = false) Integer sort,
                                        @RequestParam(name = "u", required = false, defaultValue = "false") Boolean isUser,
                                        @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(isUser && userDetails!=null)
            memberId = userDetails.getId();

        return service.getExchangeList(memberId,query,sort);
    }

    @GetMapping("rfnd")
    public List<OrderView> refundList(@RequestParam(name = "q", required = false) String query,
                                      @RequestParam(name = "s", required = false, defaultValue = "false") Boolean sort,
                                      @RequestParam(name = "u", required = false, defaultValue = "false") Boolean isUser,
                                      @AuthenticationPrincipal WebUserDetails userDetails) {

        Long memberId = null;
        if(isUser && userDetails!=null)
            memberId = userDetails.getId();

        return service.getRefundList(memberId,query,sort);
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
        return locationService.getHistoryByOrderID(orderId);
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

    @PostMapping("/cancel")
    public Boolean addCancel(@RequestBody Order order) {

        //난수 제작 :날짜+4자리난수+4자리난수
        //오늘 날짜 제작
        LocalDate currentDate = LocalDate.now();

        // 날짜를 문자열로 변환하기 (yyyyMMdd 형식)
        String dateString = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //난수 두세트 제작
        Random random = new Random();
        int randomNum1 = random.nextInt(10000);
        int randomNum2 = random.nextInt(10000);
        String randomNumber1 = String.format("%04d", randomNum1);
        String randomNumber2 = String.format("%04d", randomNum2);

        String str = dateString + randomNumber1 + randomNumber2;
        Long id = Long.parseLong(str);

        boolean valid = service.addCancel(id);

        if(valid){
            order.setCancelId(id);
            return service.edit(order);
        }


        return false;
    }

}
