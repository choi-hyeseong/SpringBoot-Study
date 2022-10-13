package com.division.springbootstudy.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authentication) throws IOException, ServletException {
        System.out.println("failed to login " + authentication.getLocalizedMessage());
        request.setAttribute("error", authentication.getLocalizedMessage());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/error");
        dispatcher.forward(request,response); //post방식으로 요청을 했으니 post로 요청을 보냄.
    }
}
