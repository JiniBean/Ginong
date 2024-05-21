package kr.co.ginong.web.config.security;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ginong.web.entity.cart.Cart;
import kr.co.ginong.web.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WebSigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    static final String REQUEST_PARAM_NAME = "rememberUsername";            //signin.html 아이디 저장 체크박스 name

    static final String COOKIE_NAME = "saved_username";                     //쿠키 이름

    static final int DEFAULT_MAX_AGE = 60*60*24*14;                         //60초*60분*24시간*14일=>14일의 생명주기

    private int maxAge = DEFAULT_MAX_AGE;                                   //쿠키의 생명주기 설정

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Autowired
    CartService cartService;


    @Override               //인증에 성공 했을때 처리할 로직 작성할 메소드
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {



        String remember = request.getParameter(REQUEST_PARAM_NAME);                             //signin 페이지에 아이디 저장 체크 박스를 누르면 "on"이 들어옴
        if ("on".equals(remember)) {                                                            //체크박스를 체크 했으면
            String username = ((WebUserDetails) authentication.getPrincipal()).getUsername();   //로그인시 사용했던 username 을 꺼내온다.
            Cookie cookie = new Cookie(COOKIE_NAME, username);                                  //Cookie 생성 후 saved_username 이라는 이름으로 username 을 담아준다.
            cookie.setMaxAge(maxAge);                                                           //생명주기를 2주로 설정
            cookie.setPath("/signin");                                                          //saved_username cookie 를 사용할 url 설정
            response.addCookie(cookie);                                                         //HttpServletResponse response에 cookie 추가
        }
        else {                                                                                  //signin 페이지 아이디 저장 체크 박스를 누르지 않는다면
            Cookie cookie = new Cookie(COOKIE_NAME, "");                                        //빈 문자열의 cookie 를 생성, 생명주기도 없는체 만든다.
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        String targetUrl = determineTargetUrl(request);                                         //기존 가려고 했던 페이지로 로그인 완료 후 가기 위한 메소드 호출
        getRedirectStrategy().sendRedirect(request, response, targetUrl);                       //determineTargetUrl 에서 얻어온 url 로 리다이렉트 해준다.

        // ######### 로그인 시 쿠키에 장바구니 데이터가 있다면 DB에 업데이트 하는 기능 ##############
//        Long userId = ((WebUserDetails) authentication.getPrincipal()).getId();
//
//        // 로그인한 회원의 DB 장바구니 정보 가져오기
//        List<Cart> cartListDB = cartService.getList(userId);
//        System.out.println();
//        System.out.println("#############################################");
//        System.out.println("cartListDB.toString() =>" + cartListDB.toString());
//        System.out.println("#############################################");
//        System.out.println();
//
//        // 쿠키에서 장바구니 데이터 가져오기
//        List<Cart> cartListCookie = getCartCookie(request, userId);
//
//        // DB의 장바구니 목록과 쿠키의 장바구니 목록을 비교 ( 기존 장바구니에 담겨있는 상품 = UPDATE / 새로 담은 상품 = INSERT )
//        List<Cart> cartUpdate = new ArrayList<>();
//        List<Cart> cartInsert = new ArrayList<>();
//
//        for (Cart cartCookie : cartListCookie) {
//            boolean found = false;
//            for (Cart cart : cartListDB) {
//                if (cart.getProductId().equals(cartCookie.getProductId())) {
//                    // DB에 이미 존재하는 항목이면 업데이트 목록에 추가
//                    // cartDB.setQuantity(cartDB.getQuantity() + cartCookie.getQuantity());   기존 상품 개수 + 새로 담은 상품 개수
//                    cart.setQuantity(cartCookie.getQuantity());                          // 새로 담은 값으로 덮어쓰기
//                    cartUpdate.add(cart);
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) {
//                // DB에 존재하지 않는 항목이면 삽입 목록에 추가
//                cartInsert.add(cartCookie);
//            }
//        }
//
//        for(Cart cart : cartInsert) System.out.println(cart.toString());
//        for(Cart cart : cartUpdate) System.out.println(cart.toString());

        // ##############################################################################


        super.onAuthenticationSuccess(request, response, authentication);                       //onAuthenticationSuccess 에 정의 된 나머지 동작들을 수행
    }


    protected String determineTargetUrl(HttpServletRequest request) {                            // 사용자가 원래 가려고 했던 페이지를 가져오는 로직 구현
        String targetUrl = "/"; // 기본 페이지 설정

        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, null);     // 세션에서 원래의 요청 URL 가져오기
        if (savedRequest != null) {                                                             // 세션에서 저장된 targetUrl이 있다면 그것을 반환
            targetUrl = savedRequest.getRedirectUrl();
        }
                                                                                                //없다면 기본 페이지로 이동하도록 설정
        return targetUrl;
    }

//    protected List<Cart> getCartCookie(HttpServletRequest request, Long userId) throws UnsupportedEncodingException, JsonProcessingException {
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            System.err.println("쿠키가 없습니다");
//            return null;
//        }
//
//        for (Cookie cookie : cookies) {
//            if ("cartList".equals(cookie.getName())) {
//                // 쿠키 값을 디코딩
//                String decodedValue = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());
//
//                // JSON 문자열을 Map 리스트로 변환
//                ObjectMapper objectMapper = new ObjectMapper();
//                List<Map<String, Object>> cartDataList = objectMapper.readValue(decodedValue, new TypeReference<List<Map<String, Object>>>() {});
//
//                // Map 리스트를 Cart 객체 리스트로 변환
//                List<Cart> cartList = new ArrayList<>();
//                for (Map<String, Object> cartData : cartDataList) {
//                    Cart cart = Cart.builder()
//                            .productId(((Number) cartData.get("prdId")).longValue())
//                            .quantity((Integer) cartData.get("quantity"))
//                            .memberId(userId)
//                            .build();
//                    cartList.add(cart);
//                }
//                return cartList;
//            }
//        }
//
//        System.err.println("cartList 쿠키를 찾을 수 없습니다");
//        return null;
//    }


}
