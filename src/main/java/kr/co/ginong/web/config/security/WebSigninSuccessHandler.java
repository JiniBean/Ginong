package kr.co.ginong.web.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebSigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    static final String REQUEST_PARAM_NAME = "rememberUsername";

    static final String COOKIE_NAME = "saved_username";

    static final int DEFAULT_MAX_AGE = 60*60*24*7;

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
        super.onAuthenticationSuccess(request, response, authentication);

    }

}
