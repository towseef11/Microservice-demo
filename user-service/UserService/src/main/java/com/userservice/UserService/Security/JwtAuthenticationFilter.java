package com.userservice.UserService.Security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.userservice.UserService.Service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }
    
    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain FilterChain
    ) throws ServletException, IOException
     {
        String authHeader =  request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            FilterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if(!jwtService.validate(token)){
            FilterChain.doFilter(request, response);
            return;
        }

        String email = jwtService.extractMail(token);
        String role = jwtService.extractRole(token);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);

        List<SimpleGrantedAuthority> authorities = List.of(authority);

        UsernamePasswordAuthenticationToken authentication = 
              new UsernamePasswordAuthenticationToken(email,null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);      

        FilterChain.doFilter(request, response);
    }
    
}
