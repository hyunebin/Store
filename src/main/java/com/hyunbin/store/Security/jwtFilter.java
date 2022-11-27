package com.hyunbin.store.Security;

import Config.JwtAuthenticationProvider;
import Config.common.UserVO;
import com.hyunbin.store.User.Repository.CustomerRepository;
import com.hyunbin.store.User.Service.CustomerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class jwtFilter implements Filter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if(!jwtAuthenticationProvider.validateToken(token)){
            throw new ServletException("Invalid Access");
        }
        UserVO userVO = jwtAuthenticationProvider.getUserVo(token);
        customerRepository.findByIdAndEmail(userVO.getId(), userVO.getEmail()).orElseThrow(()->new ServletException("Invalid Access"));

        chain.doFilter(request,response);
    }
}
