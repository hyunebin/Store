package com.hyunbin.store.Security;

import Config.JwtAuthenticationProvider;
import Config.common.UserVO;
import com.hyunbin.store.User.Repository.CustomerRepository;
import com.hyunbin.store.User.Repository.SellerRepository;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/seller/*")
@RequiredArgsConstructor
public class SellerJwtFilter implements Filter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final SellerRepository sellerRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if(!jwtAuthenticationProvider.validateToken(token)){
            throw new ServletException("Invalid Access");
        }
        UserVO userVO = jwtAuthenticationProvider.getUserVo(token);
        sellerRepository.findByIdAndEmail(userVO.getId(), userVO.getEmail()).orElseThrow(()->new ServletException("Invalid Access"));

        chain.doFilter(request,response);
    }
}
