package kr.co.ginong.web.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebSigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    static final String REQUEST_PARAM_NAME = "rememberUsername";

    static final String COOKIE_NAME = "saved_username";

    static final int DEFAULT_MAX_AGE = 60*60*24*14;

    private int maxAge = DEFAULT_MAX_AGE;

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {



        String remember = request.getParameter(REQUEST_PARAM_NAME);
        if ("on".equals(remember)) {
            String username = ((WebUserDetails) authentication.getPrincipal()).getUsername();
            Cookie cookie = new Cookie(COOKIE_NAME, username);
            cookie.setMaxAge(maxAge);
            cookie.setPath("/signin");
            response.addCookie(cookie);
        }
        else {
            Cookie cookie = new Cookie(COOKIE_NAME, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        String targetUrl = determineTargetUrl(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

        super.onAuthenticationSuccess(request, response, authentication);
    }


    protected String determineTargetUrl(HttpServletRequest request) {
        // 사용자가 원래 가려고 했던 페이지를 가져오는 로직 구현
        // 예를 들어, 세션에서 저장된 targetUrl이 있다면 그것을 반환하고, 없다면 기본 페이지로 이동하도록 설정
        String targetUrl = "/"; // 기본 페이지 설정
        // 세션에서 원래의 요청 URL 가져오기
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, null);
        if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();
        }
        return targetUrl;
    }
}
