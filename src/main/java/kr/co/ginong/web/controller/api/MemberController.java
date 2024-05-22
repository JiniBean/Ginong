package kr.co.ginong.web.controller.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import kr.co.ginong.web.config.security.WebUserDetails;
import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.member.MemberRole;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderCategory;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.entity.product.ProductView;
import kr.co.ginong.web.service.member.MemberService;
import kr.co.ginong.web.service.order.LocationService;

import kr.co.ginong.web.service.order.OrderService;
import kr.co.ginong.web.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController("apiMemberController")
@RequestMapping("api/member")
public class MemberController {

    @Autowired
    MemberService service;

    @Autowired
    private LocationService locationService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping("checkUserName")
    public ResponseEntity<Boolean> checkUserName(
            @RequestParam(name = "userName", required = false) String userName
    ) {

        Member member = service.get(userName);

        if (member!= null)
            return ResponseEntity.ok(true); //아이디 있다

        return ResponseEntity.badRequest().body(false); //아이디 없다

    }

    @PostMapping("add")
    public ResponseEntity<Boolean> add(
            @RequestBody String params
    ) {

        if(params.isBlank())
            return ResponseEntity.badRequest().body(false);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(params, JsonObject.class);

        // "userInfo" 키에 해당하는 값을 추출하여 JsonArray 객체로 변환
        JsonArray userInfoArray = jsonObject.getAsJsonArray("userInfo");

        //step1
        jsonObject = (JsonObject) userInfoArray.get(0);
        String agreeStr = String.valueOf(jsonObject.get("agree")).replace("\"", "");

        boolean agree=false;

        if(agreeStr.equals("Y"))
            agree=true;

        //step2
        jsonObject = (JsonObject) userInfoArray.get(1);
        String name = String.valueOf(jsonObject.get("name")).replace("\"", "");
        String email = String.valueOf(jsonObject.get("email")).replace("\"", "");
        String dateString = String.valueOf(jsonObject.get("birthDate")).replace("\"", "");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date birthDate=null;

        try {
            birthDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        String phone = String.valueOf(jsonObject.get("phone")).replace("\"", "");

        //step3
        //BCryptPasswordEncoder를 생성
        PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

        jsonObject = (JsonObject) userInfoArray.get(2);
        String userName = String.valueOf(jsonObject.get("userName")).replace("\"", "");
        String pwd = String.valueOf(jsonObject.get("pwd")).replace("\"", "");
        //입력받은 pwd를 encoder를 이용하여 bcrypt 형식으로 encode 후 encodePwd 에 저장
        String encodePwd = pwdEncoder.encode(pwd);
        String joinRoute = String.valueOf(jsonObject.get("joinRoute")).replace("\"", "");

        Long memberId = null;

        //회원등록
        {

            Member member = Member.builder()
                    .name(name)
                    .userName(userName)
                    .pwd(encodePwd)
                    .email(email)
                    .phone(phone)
                    .agree(agree)
                    .birthDate(birthDate)
                    .build();

            memberId = service.addMember(member);


            if (memberId == null)
                return ResponseEntity.badRequest().body(false);

            MemberRole memberRole = MemberRole.builder()
                    .memberId(memberId)
                    .roleName("ROLE_MEMBER")
                    .build();
            service.grantAuthority(memberRole);


        }

        //가입경로 등록
        {

            boolean isValid =false;

            //가입경로 선택했다면 등록
            if(!joinRoute.equals("default")){

                //실패시 false
                isValid = service.addRoute(memberId,joinRoute);

                if(!isValid)
                    return ResponseEntity.badRequest().body(false);

            }

        }

        return ResponseEntity.ok(true);
    }

    @GetMapping("userinfo")
    public Member getMember(
            @AuthenticationPrincipal WebUserDetails userDetails
    ){
        Long memberId = 0L; //사용하고 싶은 자료형으로 초기값 설정

        // 사용자가 인증되었는지 확인
        if (userDetails != null)
            memberId = userDetails.getId(); //사용하고 싶은 정보 담기

        Member member = service.get(memberId);

        return member;
    }

    /* 기본배송지가 아닌 배송지의 목록 불러 옴*/
    @GetMapping("location/list")
    public  ResponseEntity<Map<String, Object>> getList(
            @AuthenticationPrincipal WebUserDetails userDetails
    ){
        Long memberId = 0L; //사용하고 싶은 자료형으로 초기값 설정

        // 사용자가 인증되었는지 확인
        if (userDetails != null)
            memberId = userDetails.getId(); //사용하고 싶은 정보 담기

        List<Location> list  = locationService.getListByMemberID(memberId);

        Map<String, Object> response = new HashMap<>();

        if (list == null) {
            response.put("ok", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("ok", true);
        response.put("list", list);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /* 기본배송지 불러 옴*/
    @GetMapping("location/defaultList")
    public  ResponseEntity<Map<String, Object>>  getDefaultList(
            @AuthenticationPrincipal WebUserDetails userDetails
    ){
        Long memberId = 0L; //사용하고 싶은 자료형으로 초기값 설정

        // 사용자가 인증되었는지 확인
        if (userDetails != null)
            memberId = userDetails.getId(); //사용하고 싶은 정보 담기

        Location location = locationService.getByMemberID(memberId);

        Map<String, Object> response = new HashMap<>();

        if (location == null) {
            response.put("ok", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("ok", true);
        response.put("location", location);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //배송지 삭제
    @GetMapping("location/removeItem")
    public ResponseEntity<Boolean>  removeItem(
            Long locationId
    ){
        Integer state = locationService.removeLocationById(locationId);

        if(state<0)
            return ResponseEntity.badRequest().body(false);

        return ResponseEntity.ok(true);

    }

    @GetMapping("orderInfo")
    public Order getOrderInfo(@AuthenticationPrincipal WebUserDetails userDetails){
        Long memberId = 0L; //사용하고 싶은 자료형으로 초기값 설정
        // 사용자가 인증되었는지 확인
        if (userDetails != null) {
            memberId = userDetails.getId(); //사용하고 싶은 정보 담기
        }
        Order recentOrder = orderService.getRecentOrder(memberId);

        return recentOrder;
    }

    @GetMapping("categoryList")
    public List<OrderCategory> categories() {
        List<OrderCategory> categoryList = orderService.getCategories();

        return categoryList;
    }




    @GetMapping("pickProductList")
    public List<ProductView> getPickProductList(){

        List<ProductView> pickProductList = productService.getPickProductList();

        return pickProductList;
    }

}