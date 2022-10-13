package com.division.springbootstudy.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        System.out.println("login success" + authentication.toString());
        RequestCache cache = new HttpSessionRequestCache(); //스프링 시큐리티 캐시
        SavedRequest savedRequest = cache.getRequest(request, response);
        HttpSession session = request.getSession();
        if (session != null) {
            System.out.println("session : " + session);
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION); //에러 attribute 제거
        }
        String redirect; //리다이렉트 uri
        if (savedRequest != null) {
            redirect = savedRequest.getRedirectUrl();
            cache.removeRequest(request, response);
        }
        else
            redirect = "/board";
        response.sendRedirect(redirect);

    }
}
