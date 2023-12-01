package com.udemy.common.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtSecurityFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (StringUtils.isBlank(header) || !header.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }

            String token = header.substring(7);
            System.out.println("token = " + token);
            Claims claims = jwtUtils.claims(token);
            String phoneNumber = claims.getSubject();

            System.out.println("phoneNumber = " + phoneNumber);

            UserDetails userDetails = userDetailsService.loadUserByUsername(phoneNumber);
            System.out.println("userDetails = " + userDetails);

            var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);

        }catch (Exception e){
            logger.error(e.getMessage(),e);

            filterChain.doFilter(request,response);
        }
    }
}
